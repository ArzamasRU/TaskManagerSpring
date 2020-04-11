package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.bootstrap.Bootstrap;
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
import ru.lavrov.tm.repository.ProjectRepositoryImpl;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public final class TaskServiceImpl extends AbstractService implements ITaskService {

    public TaskServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        @Nullable User user = null;
        @Nullable Project project = null;
        try {
            user = userRepository.findOne(userId);
            project = projectRepository.findEntityByName(userId, projectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (user == null)
                throw new UserNotExistsException();
            if (project == null)
                throw new ProjectNotExistsException();
            entityManager.getTransaction().begin();
            taskRepository.persist(new Task(user, taskName, project));
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeTaskByName(@NotNull final String userId, @NotNull final String taskName) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Task task = taskRepository.findEntityByName(userId, taskName);
            taskRepository.removeTask(task);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeTask(@NotNull final String userId, @NotNull final String taskId) {
        if (taskId == null || taskId.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Task task = taskRepository.findOne(userId, taskId);
            taskRepository.removeTask(task);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Task> taskList = taskRepository.findAll(userId);
            taskRepository.removeAll(taskList);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @Nullable Project project = null;
        @Nullable Task task = null;
        try {
            project = projectRepository.findEntityByName(userId, projectName);
            task = taskRepository.findEntityByName(userId, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            entityManager.getTransaction().begin();
            if (project == null)
                throw new ProjectNotExistsException();
            if (task != null)
                throw new TaskNameExistsException();
            taskRepository.renameTask(userId, project.getId(), oldName, newName);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void persist(@NotNull final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            taskRepository.persist(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void merge(@NotNull final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            taskRepository.merge(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Task> findAllByNamePart(@NotNull final String userId, @NotNull final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            return taskRepository.findAllByNamePart(userId, "%" + name + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            return taskRepository.findAllByDescPart(userId, "%" + description + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public @Nullable Collection<Task> findAll(@NotNull String userId, @Nullable Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Collection<Task> list = null;
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            list = taskRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            return taskRepository.findEntityByName(userId, taskName);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public @Nullable Collection<Task> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            return taskRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }
}
