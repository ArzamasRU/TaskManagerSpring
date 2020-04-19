package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.lavrov.tm.api.repository.IUserRepository;
import ru.lavrov.tm.api.service.ISessionService;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.security.SessionIsInvalidException;
import ru.lavrov.tm.exception.security.SessionSignIsInvalidException;
import ru.lavrov.tm.exception.security.SessionTimeIsOverException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.exception.user.UserRoleIsInvalidException;

import java.util.Collection;

import static ru.lavrov.tm.util.SignUtil.getSign;

@Service
public final class SessionServiceImpl implements ISessionService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Environment environment;

    @Override
    public void validate(@NotNull final Session session, @Nullable final Collection<Role> roles){
        if (session == null)
            throw new SessionIsInvalidException();
        if (System.currentTimeMillis() - session.getTimeStamp() > 60000) {
            throw new SessionTimeIsOverException();
        }
        validatePermission(session, roles);
        @Nullable final String currSign = session.getSign();
        session.setSign(null);
        @Nullable final String resultSign = getSign(session, environment.getProperty("salt"),
                Integer.parseInt(environment.getProperty("cycle")));
        if (resultSign == null || resultSign.isEmpty())
            throw new SessionSignIsInvalidException();
        if (!resultSign.equals(currSign))
            throw new SessionSignIsInvalidException();
        session.setSign(currSign);
    }

    @Override
    public @Nullable Session login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @Nullable User user = null;
        try {
            user = userRepository.findByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null)
            return null;
        if (!password.equals(user.getPassword()))
            return null;
        @NotNull final Session session = new Session(user.getId(), user.getRole(), System.currentTimeMillis());
        session.setSign(getSign(session, environment.getProperty("salt"),
                Integer.parseInt(environment.getProperty("cycle"))));
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

