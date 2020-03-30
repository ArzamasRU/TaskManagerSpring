package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

public interface IUserRepository {
    void updatePassword(@Nullable String userId, @Nullable String newPassword);

    void updateLogin(@Nullable String userId, @Nullable String newLogin);

    User findUserByLogin(@Nullable String login);

    User findOne(@Nullable String userId);

    void removeUser(@Nullable final String userId);

    void persist(@Nullable User entity);

    void merge(@Nullable User entity);

    void removeAll(@Nullable String userId);

    Collection<User> findAll(@Nullable String userId);
}