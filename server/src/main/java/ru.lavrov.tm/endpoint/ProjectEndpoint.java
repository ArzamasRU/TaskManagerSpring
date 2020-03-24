package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Comparator;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.ProjectEndpoint")
public final class ProjectEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/ProjectEndpoint?wsdl";

    public ProjectEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public boolean createByProjectName(@Nullable final Session session, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        projectService.createByProjectName(session.getUserId(), projectName);
        return true;
    }

    @WebMethod
    public boolean removeProjectByName(@Nullable final Session session, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        projectService.removeProjectByName(session.getUserId(), projectName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Task> getProjectTasks(@Nullable final Session session, @Nullable final String projectName) {
        if (session == null)
            return null;
        if (projectName == null || projectName.isEmpty())
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.getProjectTasks(session.getUserId(), projectName);
    }

    @WebMethod
    public boolean renameProject(
            @Nullable final Session session, @NotNull final String oldName, @NotNull final String newName
    ) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        projectService.renameProject(session.getUserId(), oldName, newName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByNamePart(@Nullable final Session session, @Nullable final String name) {
        if (name == null || name.isEmpty())
            return null;
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByDescPart(@Nullable final Session session, @Nullable final String description) {
        if (description == null || description.isEmpty())
            return null;
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAll(@Nullable final Session session) {
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
    public Collection<Project> findAllByStatus(@Nullable final Session session) {
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StatusComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByFinishDate(@Nullable final Session session) {
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByStartDate(@Nullable final Session session) {
        if (session == null)
            return null;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return null;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public boolean removeAll(@Nullable final Session session) {
        if (session == null)
            return false;
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        projectService.removeAll(session.getUserId());
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
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        projectService.remove(session.getUserId(), entityId);
        return true;
    }
}
