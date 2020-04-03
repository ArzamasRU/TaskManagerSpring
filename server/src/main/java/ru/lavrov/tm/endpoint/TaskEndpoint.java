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
import ru.lavrov.tm.dto.Session;
import ru.lavrov.tm.dto.Task;
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

    public TaskEndpoint(final IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public boolean createByTaskName(
            @Nullable final String token, @NotNull final String taskName, @NotNull final String projectName
    ) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (taskName == null || taskName.isEmpty())
            return false;
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.createByTaskName(session.getUserId(), taskName, projectName);
        return true;
    }

    @WebMethod
    public boolean removeTaskByName(@Nullable final String token, @NotNull final String taskName) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (taskName == null || taskName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeTaskByName(session.getUserId(), taskName);
        return true;
    }

    @WebMethod
    public boolean renameTask(@Nullable final String token,
                              @NotNull final String projectName,
                              @NotNull final String oldName,
                              @NotNull final String newName
    ) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (projectName == null || projectName.isEmpty())
            return false;
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.renameTask(session.getUserId(), projectName, oldName, newName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByNamePart(@Nullable final String token, @NotNull final String name) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (name == null || name.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByDescPart(@Nullable final String token, @NotNull final String description) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (description == null || description.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasks(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByStatus(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StatusComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByStartDate(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByFinishDate(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public boolean removeAll(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeAll(session.getUserId());
        return true;
    }

    @WebMethod
    public boolean remove(@Nullable final String token, @NotNull final String entityId) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        if (entityId == null || entityId.isEmpty())
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        taskService.removeTask(session.getUserId(), entityId);
        return true;
    }
}
