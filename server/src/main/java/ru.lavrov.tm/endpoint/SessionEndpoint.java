package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.SessionEndpoint")
public final class SessionEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/SessionEndpoint?wsdl";

    public SessionEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

//    @WebMethod
//    @NotNull
//    public boolean verifySession(@Nullable final Session session){
//        @NotNull final SessionService sessionService = bootstrap.getSessionService();
//        return sessionService.verifySession(session);
//    }

    @WebMethod
    public Session login(@NotNull final String login, @NotNull final String password){
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();

        System.out.println(login);
        System.out.println(password);

        //        @Nullable final User user =  userService.findUserByLogin(login);
//        if (login.equals(user.getLogin()))
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        userService.loginUser(login, password);
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        @Nullable final Session session = sessionService.login(login, password);
        return session;
    }

//    @WebMethod
//    @NotNull
//    public boolean verifySession(){
//        return true;
//    }
}
