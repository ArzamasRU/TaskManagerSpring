package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lavrov.tm.api.service.*;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.dto.UserDTO;
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
@Component
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint {

    @NotNull
    public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @WebMethod
    public void registerUser(@Nullable final String login,
                             @Nullable final String password,
                             @Nullable final String role){
        userService.createByLogin(login, password, role);
    }

    @WebMethod
    public void deleteUser(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        userService.removeUser(session.getUserId());
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> getUserProjects(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return Project.getProjectDTO(projectService.findAll(session.getUserId()));
    }

    @WebMethod
    public @Nullable Collection<TaskDTO> getUserTasks(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return Task.getTaskDTO(taskService.findAll(session.getUserId()));
    }

    @WebMethod
    public @Nullable UserDTO getUser(@Nullable final String token){
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        return User.getUserDTO(userService.findOne(session.getUserId()));
    }
}
