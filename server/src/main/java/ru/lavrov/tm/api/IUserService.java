package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

public interface IUserService extends IService {
    void createByLogin(@Nullable final User user);

//    void updatePassword(@Nullable String userId, @Nullable String newPassword);
//
//    void updateLogin(@Nullable String userId, @Nullable String newLogin);
//
//    User findOne(@Nullable String userId);
//
    @Nullable User findUserByLogin(@Nullable String login);

    void removeUser(@Nullable final String userId);
//
//    void persist(@Nullable User entity);
//
//    void merge(@Nullable User entity);
//
//    Collection<User> findAll(@Nullable String userId);
}
