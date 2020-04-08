package ru.lavrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
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
        } finally {
            entityManager.close();
        }
    }

//    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (newPassword == null || newPassword.isEmpty())
//            throw new UserPasswordIsInvalidException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        try {
//            userRepository.updatePassword(userId, newPassword);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    public void updateLogin(@Nullable final String userId, @Nullable final String newLogin) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (newLogin == null || newLogin.isEmpty())
//            throw new UserLoginIsInvalidException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        @Nullable final User user = userRepository.findUserByLogin(newLogin);
//        if (user != null)
//            throw new UserLoginExistsException();
//        try {
//            userRepository.updateLogin(userId, newLogin);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Nullable
//    @Override
//    public User findOne(@Nullable final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        return userRepository.findOne(userId);
//    }
//
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
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
//
//    @Override
//    public void removeUser(@Nullable final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        try {
//            userRepository.removeUser(userId);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void persist(@Nullable final User entity) {
//        if (entity == null)
//            throw new ProjectNotExistsException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        try {
//            userRepository.persist(entity);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void merge(@Nullable final User entity) {
//        if (entity == null)
//            throw new ProjectNotExistsException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        try {
//            userRepository.persist(entity);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Nullable
//    @Override
//    public Collection<User> findAll(@Nullable final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
//        return userRepository.findAll(userId);
//    }
}
