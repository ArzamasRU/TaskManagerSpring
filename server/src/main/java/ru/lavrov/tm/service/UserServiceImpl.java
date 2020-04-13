package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lavrov.tm.api.repository.IUserRepository;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.db.RequestIsFailedException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.Collection;

public final class UserServiceImpl extends AbstractService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public void createByLogin(
            @NotNull final String login, @NotNull final String password, @NotNull final String role
    ) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        if (role == null || role.isEmpty())
            throw new UserRoleIsInvalidException();
        @Nullable Role currentRole = Role.getByRole(role);
        if (currentRole == null)
            throw new UserRoleIsInvalidException();
        @Nullable User user = null;
        try {
            user = userRepository.findByLogin(login);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        try {
            if (user != null)
                throw new UserLoginExistsException();
            userRepository.persist(new User(login, password, currentRole));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void updatePassword(@NotNull final String userId, @NotNull final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        try {
            userRepository.updatePassword(userId, newPassword);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        }
    }

    @Override
    public void updateLogin(@NotNull final String userId, @NotNull final String newLogin) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        try {
            userRepository.updateLogin(userId, newLogin);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        }
    }

    @Override
    public void removeUser(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final User user = userRepository.getOne(userId);
            userRepository.removeUser(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void persist(@NotNull final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            userRepository.persist(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        }
    }

    @Override
    public void merge(@NotNull final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            userRepository.merge(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        }
    }

    @Override
    public @Nullable User findOne(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            return userRepository.getOne(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable User findUserByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        try {
            return userRepository.findByLogin(login);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable Collection<User> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            return userRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
