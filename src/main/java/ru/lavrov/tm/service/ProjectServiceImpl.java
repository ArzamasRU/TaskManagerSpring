package ru.lavrov.tm.service;

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
    protected final IProjectRepository projectRepository;
    protected final ITaskRepository taskRepository;
    protected final IUserRepository userRepository;

    public ProjectServiceImpl(final IProjectRepository projectRepository, final ITaskRepository taskRepository,
                              final IUserRepository userRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void createByName(final String projectName, final String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectRepository.findEntityByName(projectName, userId) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName, userId));
    }

    public void removeProjectByName(final String projectName, final String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        remove(project.getId(), userId);
        taskRepository.removeProjectTasks(project.getId(), userId);
    }

    public Collection<Task> getProjectTasks(final String projectName, final String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        final Collection<Task> collection = taskRepository.getProjectTasks(project.getId(), userId);
        return collection;
    }

    public void renameProject(final String oldName, final String newName, final String userId) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        projectRepository.renameProject(oldName, newName, userId);
    }
}

