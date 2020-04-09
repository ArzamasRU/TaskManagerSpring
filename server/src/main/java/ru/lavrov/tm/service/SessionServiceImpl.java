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
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.SignUtil.getSign;

public final class SessionServiceImpl extends AbstractService implements ISessionService {

    public SessionServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

    public void validate(@NotNull final Session session, @Nullable final Collection<Role> roles){
        if (session == null)
            throw new SessionIsInvalidException();
        if (System.currentTimeMillis() - session.getTimeStamp() > 60000) {
            throw new SessionTimeIsOverException();
        }
        validatePermission(session, roles);
        @Nullable final String currSign = session.getSign();
        session.setSign(null);
        @Nullable final String resultSign = getSign(session, appProperties.getProperty("salt"),
                appProperties.getIntProperty("cycle"));
        if (resultSign == null || resultSign.isEmpty())
            throw new SessionSignIsInvalidException();
        if (!resultSign.equals(currSign))
            throw new SessionSignIsInvalidException();
        session.setSign(currSign);
    }

    @Override
    public @NotNull Session login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        @NotNull final Session session = new Session(user.getId(), user.getRole(), System.currentTimeMillis());
        session.setSign(getSign(session, appProperties.getProperty("salt"),
                appProperties.getIntProperty("cycle")));
        return session;
    }

    @Override
    public void validatePermission(@NotNull final Session session, @Nullable final Collection<Role> listRoles) {
        if (session == null)
            throw  new SessionIsInvalidException();
        if (listRoles != null) {
            @Nullable final Role role = session.getRole();
            if (role == null)
                throw new UserRoleIsInvalidException();
            if (!listRoles.contains(role))
                throw new UserRoleIsInvalidException();
        }
    }
}

