package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.dto.UserDTO;

import java.util.Collection;

public interface IUserService extends IService {
    void createByLogin(@Nullable String login, @Nullable String password, @Nullable String role);

    void updatePassword(@Nullable String userId, @Nullable String newPassword);

    void updateLogin(@Nullable String userId, @Nullable String newLogin);

    UserDTO findOne(@Nullable String userId);

    UserDTO findUserByLogin(@Nullable String login);

    void removeUser(@Nullable final String userId);

    void persist(@Nullable UserDTO entity);

    void merge(@Nullable UserDTO entity);

    Collection<UserDTO> findAll(@Nullable String userId);
}
