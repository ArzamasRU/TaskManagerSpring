package ru.lavrov.tm.repository;

import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collection;

public class UserRepositoryImpl extends AbstractRepository implements IUserRepository {

    public UserRepositoryImpl(@NotNull final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void persist(@Nullable final User user) {
        entityManager.persist(user);
    }

    @Override
    public @Nullable User findUserByLogin(@Nullable final String login) {
        return entityManager.createQuery("FROM User WHERE login = :login", User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public void updateLogin(@Nullable final String userId,
                            @Nullable final String newLogin){
        entityManager.createQuery("UPDATE User SET login = :newLogin WHERE id = :userId", User.class)
                .setParameter("userId", userId)
                .setParameter("newLogin", newLogin)
                .getSingleResult();
    }

    @Override
    public void updatePassword(@Nullable final String userId,
                               @Nullable final String newPassword){
        entityManager
                .createQuery("UPDATE User SET passwordHash = :newPassword WHERE id = :userId", User.class)
                .setParameter("newPassword", newPassword)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public @Nullable User findOne(@Nullable final String userId){
        return entityManager.createQuery("FROM User WHERE id = :userId", User.class)
                .setParameter("userId", userId).getSingleResult();
    }

    @Override
    public void removeUser(@Nullable final String userId) {
        entityManager
                .createQuery("DELETE FROM User WHERE id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public void merge(@Nullable final User user) {
        entityManager.merge(user);
    }

    @Override
    public @Nullable Collection<User> findAll(@Nullable final String userId) {
        return entityManager.createQuery("FROM Project WHERE id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
