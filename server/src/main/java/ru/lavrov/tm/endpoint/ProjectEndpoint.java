package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Comparator;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.ProjectEndpoint")
public final class ProjectEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/ProjectEndpoint?wsdl";

    public ProjectEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public boolean createByProjectName(@Nullable final String token, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        projectService.createByProjectName(session.getUserId(), projectName);
        return true;
    }

    @WebMethod
    public boolean removeProjectByName(@Nullable final String token, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        projectService.removeProjectByName(session.getUserId(), projectName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Task> getProjectTasks(@Nullable final String token, @Nullable final String projectName) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        if (projectName == null || projectName.isEmpty())
            return null;
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.getProjectTasks(session.getUserId(), projectName);
    }

    @WebMethod
    public boolean renameProject(
            @Nullable final String token, @NotNull final String oldName, @NotNull final String newName
    ) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        projectService.renameProject(session.getUserId(), oldName, newName);
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByNamePart(@Nullable final String token, @Nullable final String name) {
        if (name == null || name.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByDescPart(@Nullable final String token, @Nullable final String description) {
        if (description == null || description.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAll(@Nullable final String token) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByStatus(@Nullable final String token) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StatusComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByFinishDate(@Nullable final String token) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<Project> findAllByStartDate(@Nullable final String token) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @Nullable final Session session = tokenService.getToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public boolean removeAll(@Nullable final String token) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        projectService.removeAll(session.getUserId());
        return true;
    }

    @WebMethod
    public boolean remove(@Nullable final String token, @NotNull final String entityId) {
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token);
        if (entityId == null || entityId.isEmpty())
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.getToken(token).getSession();
        projectService.removeProject(session.getUserId(), entityId);
        return true;
    }
}
