package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";

    @WebMethod
    public void registerUser(
            @Nullable final String login, @Nullable final String password, @Nullable final String roleName
    ){
        if (login == null || login.isEmpty())
            return;
        if (password == null || password.isEmpty())
            return;
        if (roleName == null || roleName.isEmpty())
            return;
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.createByLogin(login, password, roleName);
    }

    @WebMethod
    public void deleteUser(@Nullable final String userId){
        if (userId == null || userId.isEmpty())
            return;
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.remove(userId, userId);
    }

    @WebMethod
    @NotNull
    public Collection<Project> getUserProjects(@Nullable final String userId){
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Collection<Project> projectList = projectService.findAll(userId);
        return projectList;
    }

    @WebMethod
    @NotNull
    public Collection<Task> getUserTasks(@Nullable final String userId){
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Task> taskList = taskService.findAll(userId);
        return taskList;
    }

    @WebMethod
    @Nullable
    public User getUser(@Nullable final String userId){
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final User user = userService.findOne(userId);
        return user;
    }

    @WebMethod
    public void logoutUser(@Nullable final String userId){
        if (userId == null || userId.isEmpty())
            return;
        @NotNull final IUserService userService = bootstrap.getUserService();
//        userService.logout();
    }

    @WebMethod
    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword){
        if (userId == null || userId.isEmpty())
            return;
        if (newPassword == null || newPassword.isEmpty())
            return;
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.updatePassword(userId, newPassword);
    }

    @WebMethod
    public void updateLogin(@Nullable final String userId, @Nullable final String newLogin){
        if (userId == null || userId.isEmpty())
            return;
        if (newLogin == null || newLogin.isEmpty())
            return;
        @NotNull final IUserService userService = bootstrap.getUserService();
        userService.updateLogin(userId, newLogin);
    }
}
