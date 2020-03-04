package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class TaskRepositoryImpl extends AbstractTaskRepository {
    public void removeTaskByName(final String taskName, final String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task task = findEntityByName(taskName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        tasks.remove(task.getId());
    }

    public Collection<Task> getProjectTasks(final String projectId, final String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        List<Task> list = new ArrayList();
        for (Task task : tasks.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                list.add(task);
            }
        }
        return list;
    }

    public void removeProjectTasks(final String projectId, final String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        for (Task task : tasks.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                tasks.remove(task.getId());
            }
        }
    }

    public Task findProjectTaskByName(final String taskName, final String projectId, final String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task currentTask = null;
        for (Task task: tasks.values()) {
            if (task.getName().equals(taskName) && task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public void renameTask(final String projectId, final String oldName, final String newName, final String userId) throws RuntimeException {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task task = findEntityByName(oldName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        if (findProjectTaskByName(newName, projectId, userId) != null)
            throw new TaskExistsException();
        task.setName(newName);
    }
}
