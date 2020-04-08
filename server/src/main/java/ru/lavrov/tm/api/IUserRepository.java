package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

public interface IUserRepository {
    void updatePassword(@Nullable String userId,
                        @Nullable String newPassword);

    void updateLogin(@Nullable String userId,
                     @Nullable String newLogin);

    @Nullable User findUserByLogin(@Nullable String login);

    @Nullable User findOne(@Nullable String userId);

    void removeUser(@Nullable String userId);

    void persist(@Nullable User user);

    void merge(@Nullable User entity);

    Collection<User> findAll(@Nullable String userId);
}