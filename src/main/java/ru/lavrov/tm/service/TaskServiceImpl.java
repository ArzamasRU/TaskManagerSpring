package ru.lavrov.tm.service;

import ru.lavrov.tm.api.TaskRepository;
import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.AbstractProjectRepository;

public final class TaskServiceImpl extends AbstractTaskService {
    public TaskServiceImpl(final TaskRepository taskRepository, final AbstractProjectRepository projectRepository,
                           final UserRepository userRepository) {
        super(projectRepository, taskRepository, userRepository);
    }

    public void createByName(final String taskName, final String projectName, final String userId)
            throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        if (taskRepository.findProjectTaskByName(taskName, project.getId(), userId) != null)
            throw new TaskNameExistsException();
        persist(new Task(taskName, project.getId(), userId));
    }

    public void removeTaskByName(final String taskName, final String userId) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        taskRepository.removeTaskByName(taskName, userId);
    }

    public void renameTask(final String projectName, final String oldName, final String newName, final String userId)
            throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Project project = projectRepository.findEntityByName(newName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        taskRepository.renameTask(project.getId(), oldName, newName, userId);
    }
}
