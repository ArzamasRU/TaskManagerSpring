package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    public UserServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        @Nullable User user = null;
        try {
            user = userRepository.findUserByLogin(login);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        try {
            if (user != null)
                throw new UserLoginExistsException();
            entityManager.getTransaction().begin();
            userRepository.persist(new User(login, password, currentRole));
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updatePassword(@NotNull final String userId, @NotNull final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.updatePassword(userId, newPassword);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateLogin(@NotNull final String userId, @NotNull final String newLogin) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.updateLogin(userId, newLogin);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeUser(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(userId);
            userRepository.removeUser(user);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void persist(@NotNull final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.persist(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void merge(@NotNull final User entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.merge(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable User findOne(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            return userRepository.findOne(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public @Nullable User findUserByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            return userRepository.findUserByLogin(login);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public @Nullable Collection<User> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            return userRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }
}
