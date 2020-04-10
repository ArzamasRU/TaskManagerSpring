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
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
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

    public ProjectEndpoint(@NotNull IServiceLocator bootstrap) {
        super(bootstrap);
    }

    @WebMethod
    public void createByProjectName(@Nullable final String token, @Nullable final String projectName) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        projectService.createByProjectName(session.getUserId(), projectName);
    }

    @WebMethod
    public void removeProjectByName(@Nullable final String token, @Nullable final String projectName) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
            projectService.removeProjectByName(session.getUserId(), projectName);
    }

    @WebMethod
    public void renameProject(
            @Nullable final String token, @Nullable final String oldName, @Nullable final String newName
    ) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        projectService.renameProject(session.getUserId(), oldName, newName);
    }

    @WebMethod
    public void removeAll(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        projectService.removeAll(session.getUserId());
    }

    @WebMethod
    public void remove(@Nullable final String token, @NotNull final String entityId) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        projectService.removeProject(session.getUserId(), entityId);
    }

    @WebMethod
    public @Nullable Collection<Task> getProjectTasks(@Nullable final String token, @Nullable final String projectName) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return projectService.getProjectTasks(session.getUserId(), projectName);
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAllByNamePart(@Nullable final String token, @Nullable final String name) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return Project.getProjectDTO(projectService.findAllByNamePart(session.getUserId(), name));
    }

    @WebMethod
    public @Nullable ProjectDTO findProjectByName(@Nullable final String token, @Nullable final String name) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return Project.getProjectDTO(projectService.findProjectByName(session.getUserId(), name));
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAllByDescPart(@Nullable final String token, @Nullable final String description) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return Project.getProjectDTO(projectService.findAllByDescPart(session.getUserId(), description));
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAll(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return Project.getProjectDTO(projectService.findAll(session.getUserId()));
    }

    @WebMethod
    public @Nullable ProjectDTO findOne(@Nullable final String token, @Nullable final String entityId) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        return Project.getProjectDTO(projectService.findOne(session.getUserId(), entityId));
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAllByStatus(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StatusComparator();
        return Project.getProjectDTO(projectService.findAll(session.getUserId(), comparator));
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAllByFinishDate(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new FinishDateComparator();
        return Project.getProjectDTO(projectService.findAll(session.getUserId(), comparator));
    }

    @WebMethod
    public @Nullable Collection<ProjectDTO> findAllByStartDate(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
        tokenService.validate(token, roles);
        @NotNull final Session session = tokenService.decryptToken(token).getSession();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Comparator comparator = new StartDateComparator();
        return Project.getProjectDTO(projectService.findAll(session.getUserId(), comparator));
    }
}
