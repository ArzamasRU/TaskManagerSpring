package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public final class TaskServiceImpl extends AbstractService<Task> implements ITaskService {
    @NotNull
    protected final IProjectRepository projectRepository;
    @NotNull
    protected final ITaskRepository taskRepository;
    @NotNull
    protected final IUserRepository userRepository;

    public TaskServiceImpl(final ITaskRepository taskRepository, final IProjectRepository projectRepository,
                           final IUserRepository userRepository) {
        super(taskRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createByName(
            @Nullable final String userId, @Nullable final String taskName, @Nullable final String projectName
    ) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        if (taskRepository.findProjectTaskByName(userId, taskName, project.getId()) != null)
            throw new TaskNameExistsException();
        persist(new Task(taskName, project.getId(), userId));
    }

    @Override
    public void removeTaskByName(@Nullable final String userId, @Nullable final String taskName) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        taskRepository.removeTaskByName(userId, taskName);
    }

    @Override
    public void renameTask(
            @Nullable final String userId, @Nullable final String projectName, @Nullable final String oldName,
            @Nullable final String newName
    ) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(userId, newName);
        if (project == null)
            throw new ProjectNotExistsException();
        taskRepository.renameTask(userId, project.getId(), oldName, newName);
    }

    @Nullable
    @Override
    public Collection<Task> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Collection<Task> collection = taskRepository.findAllByNamePart(userId, name);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Task> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @Nullable final Collection<Task> collection = taskRepository.findAllByDescPart(userId, description);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Task> findAll(@Nullable String userId, @Nullable Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        return taskRepository.findAll(userId, comparator);
    }
}
