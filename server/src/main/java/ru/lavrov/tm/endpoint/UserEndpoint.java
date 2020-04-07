package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.enumerate.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";

    public UserEndpoint(final IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public boolean registerUser(@NotNull final String login,
                                @NotNull final String password,
                                @NotNull final String role){

        if (login == null || login.isEmpty())
            return false;
        if (password == null || password.isEmpty())
            return false;
        if (role == null || role.isEmpty())
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        try {
            userService.createByLogin(login, password, role);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean deleteUser(@Nullable final String token){
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            userService.removeUser(session.getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Project> getUserProjects(@Nullable final String token){
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            return projectService.findAll(session.getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Collection<Task> getUserTasks(@Nullable final String token){
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            return taskService.findAll(session.getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod
    @Nullable
    public User getUser(@Nullable final String token){
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            return userService.findOne(session.getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
