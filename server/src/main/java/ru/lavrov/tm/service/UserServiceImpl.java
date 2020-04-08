package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserNotExistsException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.Collection;

public final class UserServiceImpl extends AbstractService implements IUserService {

    public UserServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

    public void createByLogin(@Nullable final User user) {
        if (user == null)
            throw new UserNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.persist(user);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
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
        } finally {
            entityManager.close();
        }
    }

    public void updateLogin(@Nullable final String userId, @Nullable final String newLogin) {
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
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    public User findOne(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            return userRepository.findOne(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Nullable
    @Override
    public User findUserByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            return userRepository.findUserByLogin(login);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public void removeUser(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            userRepository.removeUser(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void persist(@Nullable final User entity) {
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
        } finally {
            entityManager.close();
        }
    }

    public void merge(@Nullable final User entity) {
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
        } finally {
            entityManager.close();
        }
    }

    public @Nullable Collection<User> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            return userRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
}
