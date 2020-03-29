package ru.lavrov.tm.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.util.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;

public final class UserServiceImpl extends AbstractService<User> implements IUserService {
    @NotNull
    protected final IProjectRepository projectRepository;
    @NotNull
    protected final ITaskRepository taskRepository;
    @NotNull
    protected final IUserRepository userRepository;

    public UserServiceImpl(final IUserRepository userRepository,
                           final IProjectRepository projectRepository,
                           final ITaskRepository taskRepository) {
        super(userRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

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
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user != null)
            throw new UserLoginExistsException();
        @NotNull String hashedPassword;
            hashedPassword = HashUtil.md5Hard(password);
        persist(new User(login, hashedPassword, currentRole));
        return true;
    }

    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        userRepository.updatePassword(userId, newPassword);
    }

    public void updateLogin(@Nullable final String userId, @Nullable final String newLogin) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
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
        return userRepository.findOne(userId);
    }

    @Nullable
    @Override
    public User findUserByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void removeUser(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        userRepository.removeUser(userId);
    }
}
