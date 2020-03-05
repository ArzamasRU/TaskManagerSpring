package ru.lavrov.tm.service;

import ru.lavrov.tm.api.ProjectRepository;
import ru.lavrov.tm.api.TaskRepository;
import ru.lavrov.tm.api.TaskService;
import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Collection;

public abstract class AbstractTaskService implements TaskService<Task> {
    protected final ProjectRepository projectRepository;
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;

    public AbstractTaskService(final ProjectRepository projectRepository, final TaskRepository taskRepository,
                               final UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void persist(final Task task) throws RuntimeException {
        if (task == null)
            throw new TaskNotExistsException();
        taskRepository.persist(task);
    }

    @Override
    public void merge(final Task task) throws RuntimeException {
        if (task == null)
            throw new TaskNotExistsException();
        taskRepository.merge(task);
    }

    @Override
    public void remove(final String taskId, final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (taskId == null || taskId.isEmpty())
            throw new TaskNotExistsException();
        taskRepository.remove(taskId, userId);
    }

    @Override
    public void removeAll(final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        taskRepository.removeAll(userId);
    }

    @Override
    public Collection<Task> findAllByUser(final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        return taskRepository.findAllByUser(userId);
    }
}
