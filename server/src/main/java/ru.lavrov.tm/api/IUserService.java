package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.User;

public interface IUserService extends IService<User> {
    boolean createByLogin(@Nullable String login, @Nullable String password, @Nullable String role);

    void updatePassword(@Nullable String userId, @Nullable String newPassword);

    void updateLogin(@Nullable String userId, @Nullable String newLogin);

    User findOne(@Nullable String userId);

    User findUserByLogin(@Nullable String login);
}
