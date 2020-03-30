package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.constant.SignConstant;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;

import static ru.lavrov.tm.util.SignUtil.getSign;

public final class SessionServiceImpl extends AbstractService implements ISessionService {

    public boolean isSessionValid(@Nullable final Session session){
        if (session == null)
            return false;
        if (System.currentTimeMillis() - session.getTimeStamp() > 60000) {
            return false;
        }
        @Nullable final String currentSign = getSign(session, SignConstant.SALT, SignConstant.CYCLE);
        if (currentSign == null || currentSign.isEmpty())
            return false;
        return currentSign.equals(session.getSign());
    }

    @NotNull
    @Override
    public Session login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        @Nullable final Connection connection = getConnection();
        System.out.println(connection);
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        @NotNull final Session session =
                new Session(user.getId(), user.getRole(), System.currentTimeMillis());
        session.setSign(getSign(session, SignConstant.SALT, SignConstant.CYCLE));
        return session;
    }

    @Override
    public boolean hasPermission(@Nullable final Session session, @Nullable final Collection<Role> listRoles) {
        if (listRoles == null)
            return true;
        @NotNull final Role role = session.getRole();
        if (role == null)
            throw new UserRoleIsInvalidException();
        return listRoles.contains(role);
    }
}

