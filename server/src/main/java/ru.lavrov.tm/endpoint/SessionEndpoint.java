package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.SessionEndpoint")
public final class SessionEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/SessionEndpoint?wsdl";

    public SessionEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Nullable
    public Session login(@NotNull final String login, @NotNull final String password){
        if (login == null || login.isEmpty())
            return null;
        if (password == null || password.isEmpty())
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        return sessionService.login(login, password);
    }

}
