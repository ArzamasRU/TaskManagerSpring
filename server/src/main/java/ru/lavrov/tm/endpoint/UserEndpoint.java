package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserDoNotHavePermissionException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";

    public UserEndpoint(final IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public boolean registerUser(@Nullable final Session session,
                                @NotNull final String login,
                                @NotNull final String password,
                                @NotNull final String role){

        if (login == null || login.isEmpty())
            return false;
        if (password == null || password.isEmpty())
            return false;
        if (role == null || role.isEmpty())
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        return userService.createByLogin(login, password, role);
    }

    @WebMethod
    public boolean deleteUser(@Nullable final Session session){
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.removeUser(session.getUserId());
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Project> getUserProjects(@Nullable final Session session){
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public Collection<Task> getUserTasks(@Nullable final Session session){
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            throw new UserDoNotHavePermissionException();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public User getUser(@Nullable final Session session){
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            throw new UserDoNotHavePermissionException();
        @NotNull final IUserService userService = bootstrap.getUserService();
        return userService.findOne(session.getUserId());
    }
}
