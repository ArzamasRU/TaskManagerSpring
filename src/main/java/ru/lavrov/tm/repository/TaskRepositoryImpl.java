package ru.lavrov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.entity.EntityNotExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public final class TaskRepositoryImpl extends AbstractRepository<Task> implements ITaskRepository {
    @Override
    public void removeTaskByName(@Nullable final String taskName, @Nullable final String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Task task = findEntityByName(taskName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        entities.remove(task.getId());
    }

    @Nullable
    @Override
    public Collection<Task> getProjectTasks(@Nullable final String projectId, @Nullable final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        @Nullable final List<Task> list = new ArrayList();
        for (@Nullable final Task task : entities.values()) {
            if (task == null)
                continue;
            boolean isProjectIdEquals = task.getProjectId().equals(projectId);
            boolean isTaskUserIdEquals = task.getUserId().equals(userId);
            if (isProjectIdEquals && isTaskUserIdEquals) {
                list.add(task);
            }
        }
        return list;
    }

    @Override
    public void removeProjectTasks(@Nullable final String projectId, @Nullable final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        for (@Nullable final Task task : entities.values()) {
            if (task == null)
                continue;
            boolean isProjectIdEquals = task.getProjectId().equals(projectId);
            boolean isTaskUserIdEquals = task.getUserId().equals(userId);
            if (isProjectIdEquals && isTaskUserIdEquals) {
                entities.remove(task.getId());
            }
        }
    }

    @Nullable
    @Override
    public Task findProjectTaskByName(@Nullable final String taskName, @Nullable final String projectId,
                                      @Nullable final String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        @Nullable Task currentTask = null;
        for (@Nullable final Task task: entities.values()) {
            if (task == null)
                continue;
            boolean isProjectIdEquals = task.getProjectId().equals(projectId);
            boolean isTaskUserIdEquals = task.getUserId().equals(userId);
            boolean isTaskNameEquals = task.getName().equals(taskName);
            if (isTaskNameEquals && isProjectIdEquals && isTaskUserIdEquals) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    @Nullable
    @Override
    public void renameTask(@Nullable final String projectId, @Nullable final String oldName,
                           @Nullable final String newName, @Nullable final String userId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Task task = findEntityByName(oldName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        if (findProjectTaskByName(newName, projectId, userId) != null)
            throw new TaskExistsException();
        task.setName(newName);
    }

    @Nullable
    @Override
    public Collection<Task> findAll(@Nullable final String userId, @Nullable final Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Collection<Task> list = new ArrayList<>();
        for (@Nullable final Task entity : entities.values()) {
            if (entity == null)
                continue;
            if (entity.getUserId().equals(userId))
                list.add(entity);
        }
        if (comparator == null)
            return list;
        ((ArrayList<Task>) list).sort(comparator);
        return list;
    }

    @Nullable
    @Override
    public Task findEntityByName(@Nullable final String entityName, @Nullable final String userId){
        if (entityName == null || entityName.isEmpty())
            throw new EntityNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Task currentEntity = null;
        for (@Nullable final Task entity : entities.values()) {
            if (entity == null)
                continue;
            boolean isEntityNameEquals = entityName.equals(entity.getName());
            boolean isEntityUserIdEquals = entity.getUserId().equals(userId);
            if (isEntityNameEquals && isEntityUserIdEquals) {
                currentEntity = entity;
                break;
            }
        }
        return currentEntity;
    }
}
