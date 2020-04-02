package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

public interface ITokenService {

    String login(@NotNull String login, @NotNull String password);

    void validate(@Nullable final String token, @Nullable final Collection<Role> roles);

    Token getToken(@Nullable final String token);
}
