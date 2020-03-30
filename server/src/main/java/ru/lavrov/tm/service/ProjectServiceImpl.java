package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.ProjectRepositoryImpl;
import ru.lavrov.tm.repository.TaskRepositoryImpl;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Comparator;

public final class ProjectServiceImpl extends AbstractService implements IProjectService {

    @Override
    public void createByProjectName(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        if (projectRepository.findEntityByName(userId, projectName) != null)
            throw new ProjectNameExistsException();
        projectRepository.persist(new Project(projectName, userId));
    }

    @Override
    public void removeProjectByName(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(connection);
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project != null)
            throw new ProjectNameExistsException();
        projectRepository.removeProject(userId, project.getId());
        taskRepository.removeProjectTasks(userId, project.getId());
    }

    @Override
    public void removeProject(@Nullable final String userId, @Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(connection);
        projectRepository.removeProject(userId, projectId);
        taskRepository.removeProjectTasks(userId, projectId);
    }

    @Nullable
    @Override
    public Collection<Task> getProjectTasks(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(connection);
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        @Nullable final Collection<Task> collection = taskRepository.getProjectTasks(userId, project.getId());
        return collection;
    }

    @Nullable
    @Override
    public void renameProject(
            @Nullable final String userId, @Nullable final String oldName, @Nullable final String newName
    )  {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        projectRepository.renameProject(userId, oldName, newName);
    }

    @Nullable
    @Override
    public Collection<Project> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        @Nullable final Collection<Project> collection = projectRepository.findAllByNamePart(userId, name);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Project> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        @Nullable final Collection<Project> collection = projectRepository.findAllByDescPart(userId, description);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Project> findAll(@Nullable final String userId, @Nullable final Comparator<Project> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        return projectRepository.findAll(userId, comparator);
    }

    @Override
    public void persist(@Nullable final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        projectRepository.persist(entity);
    }

    @Override
    public void merge(@Nullable final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        projectRepository.persist(entity);
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        projectRepository.removeAll(userId);
    }

    @Nullable
    @Override
    public Collection<Project> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
        return projectRepository.findAll(userId);
    }
}

