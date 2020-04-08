package ru.lavrov.tm.repository;

import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.Collection;

public class UserRepositoryImpl extends AbstractRepository implements IUserRepository {

    public UserRepositoryImpl(@NotNull final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void persist(@NotNull final User user) {
        entityManager.persist(user);
    }

    //    @Select("SELECT * FROM app_user WHERE login = #{login}")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "login", column = "login"),
//            @Result(property = "password", column = "passwordHash"),
//            @Result(property = "role", column = "role")
//    })
    @Override
    public @Nullable User findUserByLogin(@Nullable String login) {
        return entityManager.createQuery("SELECT u FROM User u WHERE login = :login", User.class)
                .setParameter("login", login).getSingleResult();
    }
}
