package ru.lavrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.api.ITokenService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.enumerate.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.ProjectEndpoint")
public final class ProjectEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/ProjectEndpoint?wsdl";

    public ProjectEndpoint(final IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public boolean createByProjectName(@Nullable final String token, @Nullable final String projectName) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            projectService.createByProjectName(session.getUserId(), projectName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean removeProjectByName(@Nullable final String token, @NotNull final String projectName) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (projectName == null || projectName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            projectService.removeProjectByName(session.getUserId(), projectName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<TaskDTO> getProjectTasks(@Nullable final String token, @Nullable final String projectName) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        if (projectName == null || projectName.isEmpty())
            return null;
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.getProjectTasks(session.getUserId(), projectName);
    }

    @WebMethod
    public boolean renameProject(
            @Nullable final String token, @NotNull final String oldName, @NotNull final String newName
    ) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            return false;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            projectService.renameProject(session.getUserId(), oldName, newName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAllByNamePart(@Nullable final String token, @Nullable final String name) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (name == null || name.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByNamePart(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public ProjectDTO findProjectByName(@Nullable final String token, @Nullable final String name) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (name == null || name.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findProjectByName(session.getUserId(), name);
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAllByDescPart(@Nullable final String token, @Nullable final String description) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        if (description == null || description.isEmpty())
            return null;
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAllByDescPart(session.getUserId(), description);
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAll(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public ProjectDTO findOne(@Nullable final String token, @Nullable final String entityId) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.findOne(session.getUserId(), entityId);
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAllByStatus(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StatusComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAllByFinishDate(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    @Nullable
    public Collection<ProjectDTO> findAllByStartDate(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return projectService.findAll(session.getUserId(), comparator);
    }

    @WebMethod
    public boolean removeAll(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            projectService.removeAll(session.getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean remove(@Nullable final String token, @NotNull final String entityId) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        if (entityId == null || entityId.isEmpty())
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        try {
            projectService.removeProject(session.getUserId(), entityId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
