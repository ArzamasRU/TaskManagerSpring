package ru.lavrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

public interface IUserService extends UserDetailsService {
    void create(@NotNull final User user);

    void createByLogin(@NotNull String login, @NotNull String password, @NotNull String role);

    void updatePassword(@NotNull String userId, @NotNull String newPassword);

    void updateLogin(@NotNull String userId, @NotNull String newLogin);

    @Nullable  User findOne(@NotNull String userId);

    @Nullable User findUserByLogin(@NotNull String login);

    void removeUser(@NotNull final String userId);

    void persist(@NotNull User entity);

    void merge(@NotNull User entity);
}
