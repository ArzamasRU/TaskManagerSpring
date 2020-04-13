package ru.lavrov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Query("UPDATE User SET password = :newPassword WHERE id = :userId")
    void updatePassword(@Param("userId") @Nullable String userId,
                        @Param("newPassword") @Nullable String newPassword);

    @Query("UPDATE User SET login = :newLogin WHERE id = :userId")
    void updateLogin(@Param("userId") @Nullable String userId,
                     @Param("newLogin") @Nullable String newLogin);

    @Nullable User findByLogin(@Nullable String login);

    void removeUser(@Nullable final User user);

    void persist(@Nullable User user);

    void merge(@Nullable User entity);

    Collection<User> findAll(@Nullable String userId);
}