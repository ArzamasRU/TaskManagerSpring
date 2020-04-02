package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Token;

import javax.jws.WebService;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TokenEndpoint")
public final class TokenEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/TokenEndpoint?wsdl";

    public TokenEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Nullable
    public String login(@NotNull final String login, @NotNull final String password){
        if (login == null || login.isEmpty())
            return null;
        if (password == null || password.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        return tokenService.login(login, password);
    }
}