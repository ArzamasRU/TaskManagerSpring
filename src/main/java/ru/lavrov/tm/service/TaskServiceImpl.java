package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

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

    public void createByName(
            @Nullable final String taskName, @Nullable final String projectName, @Nullable final String userId
    ) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        if (taskRepository.findProjectTaskByName(taskName, project.getId(), userId) != null)
            throw new TaskNameExistsException();
        persist(new Task(taskName, project.getId(), userId));
    }

    public void removeTaskByName(@Nullable final String taskName, @Nullable final String userId) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        taskRepository.removeTaskByName(taskName, userId);
    }

    public void renameTask(
            @Nullable final String projectName, @Nullable final String oldName,
            @Nullable final String newName, @Nullable final String userId
    ) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(newName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        taskRepository.renameTask(project.getId(), oldName, newName, userId);
    }

    @Nullable
    @Override
    public Collection<Task> findAllByNamePart(@Nullable final String name, @Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Collection<Task> collection = taskRepository.findAllByNamePart(name, userId);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Task> findAllByDescPart(@Nullable final String description, @Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @Nullable final Collection<Task> collection = taskRepository.findAllByDescPart(description, userId);
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
