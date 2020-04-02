package ru.lavrov.tm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.constant.SignConstant;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.util.AESUtil;
import ru.lavrov.tm.util.SignUtil;

import javax.json.JsonException;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;

import static ru.lavrov.tm.service.PropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class TokenServiceImpl extends AbstractService implements ITokenService {

    @NotNull
    private final Bootstrap bootstrap;

    public TokenServiceImpl(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public boolean isTokenValid(@Nullable final Token token){
//        if (session == null)
//            return false;
//        if (System.currentTimeMillis() - session.getTimeStamp() > 60000) {
//            return false;
//        }
//        @Nullable final String currentSign = getSign(session, appProperties.getProperty("salt"),
//                Integer.parseInt(appProperties.getProperty("cycle")));
//        if (currentSign == null || currentSign.isEmpty())
//            return false;
//        return currentSign.equals(session.getSign());
        return false;
    }

    @NotNull
    @Override
    public String login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        @NotNull final Session session =
                new Session(user.getId(), user.getRole(), System.currentTimeMillis());
        session.setSign(getSign(session, appProperties.getProperty("salt"),
                Integer.parseInt(appProperties.getProperty("cycle"))));

        @NotNull final Token token = new Token();
        token.setSession(session);
        token.setSignature(SignUtil.getSign(token, appProperties.getProperty("salt"),
                Integer.parseInt(appProperties.getProperty("cycle"))));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String jsonToken;
        try {
            jsonToken = objectMapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            throw new JsonException("Json writing is failed");
        }
        @NotNull final String encryptedToken = AESUtil.encrypt(jsonToken, appProperties.getProperty("key"));
        return encryptedToken;
    }

    @Override
    public boolean hasPermission(@Nullable final Token token, @Nullable final Collection<Role> listRoles) {
//        if (listRoles == null)
//            return true;
//        @NotNull final Role role = session.getRole();
//        if (role == null)
//            throw new UserRoleIsInvalidException();
//        return listRoles.contains(role);
        return false;
    }
}

