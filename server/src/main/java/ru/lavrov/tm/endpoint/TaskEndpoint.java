package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.enumerate.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TaskEndpoint")
public final class TaskEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/TaskEndpoint?wsdl";

    public TaskEndpoint(@NotNull IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public void createByTaskName(
            @Nullable final String token, @Nullable final String taskName, @Nullable final String projectName
    ) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.createByTaskName(session.getUserId(), taskName, projectName);
    }

    @WebMethod
    public void removeTaskByName(@Nullable final String token, @Nullable final String taskName) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeTaskByName(session.getUserId(), taskName);
    }

    @WebMethod
    public void renameTask(@Nullable final String token, 
                           @Nullable final String projectName,
                           @Nullable final String oldName,
                           @Nullable final String newName
    ) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.renameTask(session.getUserId(), projectName, oldName, newName);
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasksByNamePart(
            @Nullable final String token, @Nullable final String name
    ) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    public @Nullable Task findTaskByName(@Nullable final String token, @Nullable final String name) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findTaskByName(session.getUserId(), name);
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasksByDescPart(
            @Nullable final String token, @Nullable final String description
    ) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasks(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAll(session.getUserId());
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasksByStatus(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StatusComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasksByStartDate(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public @Nullable Collection<Task> findAllTasksByFinishDate(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public void removeAll(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeAll(session.getUserId());
    }

    @WebMethod
    public void remove(@Nullable final String token, @Nullable final String entityId) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        taskService.removeTask(session.getUserId(), entityId);
    }
}
