package ru.lavrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.security.SessionIsInvalidException;
import ru.lavrov.tm.exception.security.SessionSignIsInvalidException;
import ru.lavrov.tm.exception.security.SessionTimeIsOverException;
import ru.lavrov.tm.exception.user.*;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;

import static ru.lavrov.tm.service.PropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class SessionServiceImpl extends AbstractService implements ISessionService {

    @NotNull
    private final Bootstrap bootstrap;

    public SessionServiceImpl(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void validate(@Nullable final Session session, @Nullable final Collection<Role> roles){
        if (session == null)
            throw new SessionIsInvalidException();
        if (System.currentTimeMillis() - session.getTimeStamp() > 60000) {
            throw new SessionTimeIsOverException();
        }
        validatePermission(session, roles);
        @Nullable final String currentSign = getSign(session, appProperties.getProperty("salt"),
                Integer.parseInt(appProperties.getProperty("cycle")));
        if (currentSign == null || currentSign.isEmpty())
            throw new SessionSignIsInvalidException();
        if (currentSign.equals(session.getSign()))
            throw new SessionSignIsInvalidException();
    }

    @NotNull
    @Override
    public Session login(@NotNull final String login, @NotNull final String password) {
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
        return session;
    }

    @Override
    public void validatePermission(@Nullable final Session session, @Nullable final Collection<Role> listRoles) {
        if (listRoles != null) {
            @NotNull final Role role = session.getRole();
            if (role == null)
                throw new UserRoleIsInvalidException();
            if (!listRoles.contains(role))
                throw new UserRoleIsInvalidException();
        }
    }
}

