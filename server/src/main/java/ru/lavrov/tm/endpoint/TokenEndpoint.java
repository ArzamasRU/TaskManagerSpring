package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ITokenService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TokenEndpoint")
public final class TokenEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/TokenEndpoint?wsdl";

    public TokenEndpoint(@NotNull IServiceLocator bootstrap) {
        super(bootstrap);
    }

    public @NotNull String login(@Nullable final String login, @Nullable final String password) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        return tokenService.login(login, password);
    }
}
