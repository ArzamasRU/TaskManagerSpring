package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.dto.User;

import java.util.Collection;

public interface IUserService extends IService {
    void createByLogin(@Nullable String login, @Nullable String password, @Nullable String role);

    void updatePassword(@Nullable String userId, @Nullable String newPassword);

    void updateLogin(@Nullable String userId, @Nullable String newLogin);

    User findOne(@Nullable String userId);

    User findUserByLogin(@Nullable String login);

    void removeUser(@Nullable final String userId);

    void persist(@Nullable User entity);

    void merge(@Nullable User entity);

    Collection<User> findAll(@Nullable String userId);
}
