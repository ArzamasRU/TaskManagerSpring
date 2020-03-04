package ru.lavrov.tm.repository;

import ru.lavrov.tm.api.TaskRepository;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.task.TaskExistsException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTaskRepository implements TaskRepository<Task> {
    protected final Map<String, Task> tasks = new HashMap();

    @Override
    public Collection<Task> findAllByUser(final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        Collection<Task> list = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId))
                list.add(task);
        }
        return list;
    }

    @Override
    public void persist(final Task task) throws RuntimeException {
        String id = task.getId();
        if (tasks.containsKey(id))
            throw new TaskExistsException();
        tasks.put(id, task);
    }

    @Override
    public void merge(final Task task){
        tasks.put(task.getId(), task);
    }

    @Override
    public void remove(final String taskId, final String userId){
        if (taskId == null || taskId.isEmpty())
            throw new TaskNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        Task task = tasks.get(taskId);
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        tasks.remove(taskId);
    }

    @Override
    public void removeAll(final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        for (Task task : findAllByUser(userId)) {
            remove(task.getId(), userId);
        }
    }

    @Override
    public Task findEntityByName(final String name, final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        Task currentTask = null;
        for (Task task: tasks.values()) {
            if (task.getName().equals(name) && task.getUserId().equals(userId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }
}
