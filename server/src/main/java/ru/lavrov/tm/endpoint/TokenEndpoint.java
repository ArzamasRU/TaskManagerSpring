package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.api.service.ITaskService;
import ru.lavrov.tm.api.service.ITokenService;
import ru.lavrov.tm.api.service.IUserService;

import javax.jws.WebService;

@NoArgsConstructor
@Component
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TokenEndpoint")
public final class TokenEndpoint {

    @NotNull public static final String URL = "http://localhost:8090/TokenEndpoint?wsdl";

    @Autowired
    private ITokenService tokenService;

    public @Nullable String login(@Nullable final String login, @Nullable final String password) {
        return tokenService.login(login, password);
    }
}
