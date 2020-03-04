package ru.lavrov.tm.service;

import ru.lavrov.tm.api.ProjectRepository;
import ru.lavrov.tm.api.TaskRepository;
import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Collection;

public final class ProjectServiceImpl extends AbstractProjectService {
    public ProjectServiceImpl(final ProjectRepository projectRepository, final TaskRepository taskRepository, final UserRepository userRepository) {
        super(projectRepository, taskRepository, userRepository);
    }

    public void createByName(final String projectName, final String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectRepository.findEntityByName(projectName, userId) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName, userId));
    }

    public void removeProjectByName(final String projectName, final String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = (Project) projectRepository.findEntityByName(projectName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        remove(project.getId(), userId);
        taskRepository.removeProjectTasks(project.getId(), userId);
    }

    public Collection<Task> getProjectTasks(final String projectName, final String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = (Project) projectRepository.findEntityByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        Collection<Task> collection = taskRepository.getProjectTasks(project.getId(), userId);
        return collection;
    }

    public void renameProject(final String oldName, final String newName, final String userId) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        projectRepository.renameProject(oldName, newName, userId);
    }
}

