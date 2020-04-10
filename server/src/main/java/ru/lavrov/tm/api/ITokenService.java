package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

public interface ITokenService {

    @Nullable String login(@NotNull String login, @NotNull String password);

    void validate(@NotNull final String token, @Nullable final Collection<Role> roles);

    @NotNull Token decryptToken(@NotNull final String token);

    @NotNull String encryptToken(@NotNull final Token token);
}
