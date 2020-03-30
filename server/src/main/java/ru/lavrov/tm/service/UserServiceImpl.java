package ru.lavrov.tm.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;
import ru.lavrov.tm.util.HashUtil;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;

public final class UserServiceImpl extends AbstractService implements IUserService {

    public boolean createByLogin(
            @Nullable final String login, @Nullable final String password, @Nullable final String role
    ) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        if (role == null || role.isEmpty())
            throw new UserRoleIsInvalidException();
        @Nullable Role currentRole = null;
        boolean roleExists = false;
        for (Role curRole : Role.values())
            if (role.equals(curRole.getRole())) {
                currentRole = curRole;
                roleExists = true;
                break;
            }
        if (!roleExists)
            throw new UserRoleIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user != null)
            throw new UserLoginExistsException();
        @NotNull String hashedPassword;
            hashedPassword = HashUtil.md5Hard(password);
        userRepository.persist(new User(login, hashedPassword, currentRole));
        return true;
    }

    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        userRepository.updatePassword(userId, newPassword);
    }

    public void updateLogin(@Nullable final String userId, @Nullable final String newLogin) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        @Nullable final User user = userRepository.findUserByLogin(newLogin);
        if (user != null)
            throw new UserLoginExistsException();
        userRepository.updateLogin(userId, newLogin);
    }

    @Nullable
    @Override
    public User findOne(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        return userRepository.findOne(userId);
    }

    @Nullable
    @Override
    public User findUserByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void removeUser(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        userRepository.removeUser(userId);
    }

    @Override
    public void persist(@Nullable final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        userRepository.persist(entity);
    }

    @Override
    public void merge(@Nullable final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        userRepository.persist(entity);
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        userRepository.removeAll(userId);
    }

    @Nullable
    @Override
    public Collection<User> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(connection);
        return userRepository.findAll(userId);
    }
}
