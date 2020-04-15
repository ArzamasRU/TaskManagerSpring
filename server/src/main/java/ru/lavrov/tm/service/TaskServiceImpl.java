package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lavrov.tm.api.repository.IProjectRepository;
import ru.lavrov.tm.api.repository.ITaskRepository;
import ru.lavrov.tm.api.repository.IUserRepository;
import ru.lavrov.tm.api.service.ITaskService;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.common.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.common.NameIsInvalidException;
import ru.lavrov.tm.exception.db.RequestIsFailedException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public void createByTaskName(
            @NotNull final String userId, @NotNull final String taskName, @NotNull final String projectName
    ) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable User user = null;
        @Nullable Project project = null;
        try {
            user = userRepository.getOne(userId);
            project = projectRepository.findByUserIdAndName(userId, projectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (user == null)
                throw new UserNotExistsException();
            if (project == null)
                throw new ProjectNotExistsException();
            taskRepository.save(new Task(user, taskName, project));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Transactional
    @Override
    public void removeTaskByName(@NotNull final String userId, @NotNull final String taskName) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Task task = taskRepository.findByUserIdAndName(userId, taskName);
            taskRepository.delete(task);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Transactional
    @Override
    public void removeTask(@NotNull final String userId, @NotNull final String taskId) {
        if (taskId == null || taskId.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Task task = taskRepository.findByUserIdAndId(userId, taskId);
            taskRepository.delete(task);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Transactional
    @Override
    public void removeAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Collection<Task> taskList = taskRepository.findAllByUserId(userId);
            taskRepository.deleteAll(taskList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void renameTask(@NotNull final String userId,
                           @NotNull final String projectName,
                           @NotNull final String oldName,
                           @NotNull final String newName
    ) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project project = null;
        @Nullable Task task = null;
        try {
            project = projectRepository.findByUserIdAndName(userId, projectName);
            task = taskRepository.findByUserIdAndName(userId, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (project == null)
                throw new ProjectNotExistsException();
            if (task != null)
                throw new TaskNameExistsException();
            taskRepository.renameTask(userId, project.getId(), oldName, newName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void persist(@NotNull final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            taskRepository.save(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void merge(@NotNull final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            taskRepository.save(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public @Nullable Collection<Task> findAllByNamePart(@NotNull final String userId, @NotNull final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        try {
            return taskRepository.findByUserIdAndNameLike(userId, "%" + name + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Task> findAllByDescPart(
            @NotNull final String userId, @NotNull final String description
    ) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        try {
            return taskRepository.findByUserIdAndDescriptionLike(userId, "%" + description + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Task> findAll(@NotNull String userId, @Nullable Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Collection<Task> list = null;
        try {
            list = taskRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        if (list != null && comparator != null)
            ((ArrayList<Task>) list).sort(comparator);
        return list;
    }

    @Override
    public @Nullable Task findTaskByName(@NotNull String userId, @NotNull final String taskName) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        try {
            return taskRepository.findByUserIdAndName(userId, taskName);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Task> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            return taskRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }
}
