package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Comparator;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TaskEndpoint")
public final class TaskEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/TaskEndpoint?wsdl";

    public TaskEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public boolean createByTaskName(
            @Nullable final Session session, @NotNull final String taskName, @NotNull final String projectName
    ) {
        if (taskName == null || taskName.isEmpty())
            return false;
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.createByTaskName(session.getUserId(), taskName, projectName);
        return true;
    }

    @WebMethod
    public boolean removeTaskByName(@Nullable final Session session, @NotNull final String taskName) {
        if (taskName == null || taskName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeTaskByName(session.getUserId(), taskName);
        return true;
    }

    @WebMethod
    public boolean renameTask(@Nullable final Session session,
                              @NotNull final String projectName,
                              @NotNull final String oldName,
                              @NotNull final String newName
    ) {
        if (projectName == null || projectName.isEmpty())
            return false;
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.renameTask(session.getUserId(), projectName, oldName, newName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByNamePart(@Nullable final Session session, @NotNull final String name) {
        if (name == null || name.isEmpty())
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByDescPart(@Nullable final Session session, @NotNull final String description) {
        if (description == null || description.isEmpty())
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasks(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        return taskService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByStatus(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StatusComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByStartDate(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Task> findAllTasksByFinishDate(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return taskService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public boolean removeAll(@Nullable final Session session) {
        if (session == null)
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeAll(session.getUserId());
        return true;
    }

    @WebMethod
    public boolean remove(@Nullable final Session session, @NotNull final String entityId) {
        if (session == null)
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        if (entityId == null || entityId.isEmpty())
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        taskService.remove(session.getUserId(), entityId);
        return true;
    }
}
