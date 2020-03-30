package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

public interface ISessionService {

    boolean isSessionValid(@NotNull Session session);

    Session login(@NotNull String login, @NotNull String password);

    boolean hasPermission(@Nullable Session session, @Nullable Collection<Role> listRoles);
}
