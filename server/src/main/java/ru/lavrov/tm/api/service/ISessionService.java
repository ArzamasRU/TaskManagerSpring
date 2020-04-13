package ru.lavrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

public interface ISessionService {

    @Nullable Session login(@NotNull String login, @NotNull String password);

    void validatePermission(@NotNull Session session, @Nullable Collection<Role> listRoles);

    void validate(@NotNull final Session session, @Nullable final Collection<Role> roles);
}
