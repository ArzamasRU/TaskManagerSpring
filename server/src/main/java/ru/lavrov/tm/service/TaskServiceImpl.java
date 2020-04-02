package ru.lavrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.common.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.common.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public final class TaskServiceImpl extends AbstractService implements ITaskService {

    @NotNull
    private final Bootstrap bootstrap;

    public TaskServiceImpl(@NotNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void createByTaskName(
            @Nullable final String userId, @Nullable final String taskName, @Nullable final String projectName
    ) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        if (taskRepository.findProjectTaskByName(userId, taskName, project.getId()) != null)
            throw new TaskNameExistsException();
        try {
            taskRepository.persist(new Task(taskName, project.getId(), userId));
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeTaskByName(@Nullable final String userId, @Nullable final String taskName) {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        try {
            taskRepository.removeTaskByName(userId, taskName);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeTask(@Nullable final String userId, @Nullable final String taskId) {
        if (taskId == null || taskId.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        try {
            taskRepository.removeTask(userId, taskId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
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
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        @Nullable final Project project = projectRepository.findEntityByName(userId, newName);
        if (project == null)
            throw new ProjectNotExistsException();
        try {
            taskRepository.renameTask(userId, project.getId(), oldName, newName);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    @Override
    public Collection<Task> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
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
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        @Nullable final Collection<Task> collection = taskRepository.findAllByDescPart(userId, description);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Task> findAll(@Nullable String userId, @Nullable Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Collection<Task> list;
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        list = taskRepository.findAll(userId);
        if (comparator == null)
            return list;
        ((ArrayList<Task>) list).sort(comparator);
        return list;
    }

    @Override
    public void persist(@Nullable final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        try {
            taskRepository.persist(entity);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void merge(@Nullable final Task entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        taskRepository.persist(entity);
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        try {
            taskRepository.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    @Override
    public Collection<Task> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Connection connection = getConnection();
        if (connection == null)
            throw new ConnectionPendingException();
        @NotNull final SqlSession sqlSession = bootstrap.getSqlSessionFactory().openSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findAll(userId);
    }
}
