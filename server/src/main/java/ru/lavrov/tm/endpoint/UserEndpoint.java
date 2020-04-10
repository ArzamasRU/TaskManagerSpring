package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint extends AbstractEndpoint{

    @NotNull
    public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";

    public UserEndpoint(@NotNull IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public void registerUser(@Nullable final String login,
                             @Nullable final String password,
                             @Nullable final String role){
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.createByLogin(login, password, role);
    }

    @WebMethod
    public void deleteUser(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.removeUser(session.getUserId());
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> getUserProjects(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return Project.getProjectDTO(projectService.findAll(session.getUserId()));
    }

    @WebMethod
    public @Nullable Collection<TaskDTO> getUserTasks(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return Task.getTaskDTO(taskService.findAll(session.getUserId()));
    }

    @WebMethod
    public @Nullable User getUser(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IUserService userService = bootstrap.getUserService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return userService.findOne(session.getUserId());
    }
}
