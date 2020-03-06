package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.AbstractRepository;

import java.util.Collection;

public final class ProjectServiceImpl extends AbstractService<Project> implements IProjectService {
    @NotNull protected final IProjectRepository projectRepository;
    @NotNull protected final ITaskRepository taskRepository;
    @NotNull protected final IUserRepository userRepository;

    public ProjectServiceImpl(final IProjectRepository projectRepository, final ITaskRepository taskRepository,
                              final IUserRepository userRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createByName(@Nullable final String projectName, @Nullable final String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectRepository.findEntityByName(projectName, userId) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName, userId));
    }

    @Override
    public void removeProjectByName(@Nullable final String projectName, @Nullable final String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        remove(project.getId(), userId);
        taskRepository.removeProjectTasks(project.getId(), userId);
    }

    @Nullable
    @Override
    public Collection<Task> getProjectTasks(@Nullable final String projectName, @Nullable final String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        @Nullable final Collection<Task> collection = taskRepository.getProjectTasks(project.getId(), userId);
        return collection;
    }

    @Nullable
    @Override
    public void renameProject(@Nullable final String oldName, @Nullable final String newName,
                              @Nullable final String userId) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        projectRepository.renameProject(oldName, newName, userId);
    }
}

