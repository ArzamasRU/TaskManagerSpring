package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.User;

public interface IUserRepository extends IRepository<User> {
    void updatePassword(@Nullable String userId, @Nullable String newPassword);

    void updateLogin(@Nullable String userId, @Nullable String newLogin);

    User findUserByLogin(@Nullable String login);

    User findOne(@Nullable String userId);
}