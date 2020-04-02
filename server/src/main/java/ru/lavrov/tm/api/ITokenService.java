package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

public interface ITokenService {

    boolean isTokenValid(@NotNull Token token);

    String login(@NotNull String login, @NotNull String password);

    boolean hasPermission(@Nullable Token token, @Nullable Collection<Role> listRoles);
}
