package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sql2o.tools.NamedParameterStatement;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.db.SQLQueryIsInvalidException;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.entity.EntityNotExistsException;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

public final class TaskRepositoryImpl extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepositoryImpl(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    public void renameTask(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String oldName,
            @Nullable final String newName
    ) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Task task = findProjectTaskByName(userId, newName, projectId);
        if (task != null)
            throw new TaskNameExistsException();
        task = findProjectTaskByName(userId, oldName, projectId);
        if (task == null)
            throw new TaskNameIsInvalidException();
        @NotNull final String query = "UPDATE app_task SET  name = :name " +
                "WHERE id = :id AND user_id = :user_id AND project_id = :project_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", task.getId());
            namedParameterStatement.setString("user_id", task.getUserId());
            namedParameterStatement.setString("project_id", task.getProjectId());
            namedParameterStatement.setString("name", newName);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @NotNull
    @Override
    public Collection<Task> findAll(@Nullable final String userId, @Nullable final Comparator<Task> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<Task> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_task WHERE user_id = :user_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        if (comparator == null)
            return list;
        ((ArrayList<Task>) list).sort(comparator);
        return list;
    }

    @NotNull
    @Override
    public Collection<Task> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @NotNull final Collection<Task> list = new ArrayList<>();
        @NotNull final String query = "SELECT FROM app_task " +
                "WHERE user_id = :user_id AND description LIKE :description";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("description", description);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return list;
    }

    @NotNull
    @Override
    public Collection<Task> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @NotNull final Collection<Task> list = new ArrayList<>();
        @NotNull final String query = "SELECT FROM app_task WHERE user_id = :user_id AND name LIKE :name";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("name", name);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return list;
    }

    @Override
    public Collection<Task> getProjectTasks(@Nullable String userId, @Nullable String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<Task> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_task WHERE user_id = :user_id AND project_id = :project_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("project_id", projectId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return list;
    }

    @Override
    public void removeProjectTasks(@Nullable String userId, @Nullable String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_task WHERE user_id = :user_id AND project_id = :project_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("project_id", projectId);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @Nullable
    @Override
    public Task findProjectTaskByName(
            @Nullable final String userId, @Nullable final String entityName, @Nullable String projectId
    ) {
        if (entityName == null || entityName.isEmpty())
            throw new EntityNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Task task = null;
        @NotNull final String query = "SELECT FROM app_task " +
                "WHERE user_id = :user_id AND name = :name AND project_id = :project_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("name", entityName);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                task = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return task;
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) {
        if (taskId == null || taskId.isEmpty())
            throw new TaskNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Task task = null;
        @NotNull final String query = "SELECT * FROM app_task WHERE user_id = :user_id AND id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("id", taskId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                task = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return task;
    }

    @Override
    public void persist(@NotNull final Task task) {
        if (task == null)
            throw new TaskNotExistsException();
        @NotNull final String query =
                "INSERT INTO app_task (id, user_id, name, description, dateBegin, dateEnd) " +
                        "VALUES (:id, :user_id, :name, :description, :dateBegin, :dateEnd)";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", task.getId());
            namedParameterStatement.setString("user_id", task.getUserId());
            namedParameterStatement.setString("name", task.getName());
            namedParameterStatement.setString("description", task.getDescription());
            @Nullable final Date startDate = task.getStartDate();
            @Nullable final Date finishDate = task.getFinishDate();
            namedParameterStatement
                    .setDate("dateBegin", startDate == null ? null : new java.sql.Date(startDate.getTime()));
            namedParameterStatement
                    .setDate("dateEnd", finishDate == null ? null : new java.sql.Date(finishDate.getTime()));
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @Override
    public void merge(@NotNull final Task task) {
        if (task == null)
            throw new TaskNotExistsException();
        @NotNull final String query = "UPDATE app_task SET user_id = :user_id, name = :name, " +
                "description = :description, dateBegin = :dateBegin, dateEnd = :dateEnd, WHERE id = :id ";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", task.getId());
            namedParameterStatement.setString("user_id", task.getUserId());
            namedParameterStatement.setString("name", task.getName());
            namedParameterStatement.setString("description", task.getDescription());
            @Nullable final Date startDate = task.getStartDate();
            @Nullable final Date finishDate = task.getFinishDate();
            namedParameterStatement
                    .setDate("dateBegin", startDate == null ? null : new java.sql.Date(startDate.getTime()));
            namedParameterStatement
                    .setDate("dateEnd", finishDate == null ? null : new java.sql.Date(finishDate.getTime()));
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @Override
    public void removeTaskByName(@Nullable final String userId, @Nullable final String entityName) {
        if (entityName == null || entityName.isEmpty())
            throw new EntityNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_task WHERE user_id = :user_id AND name = :name";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("name", entityName);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @Override
    public void removeTask(@Nullable final String userId, @Nullable final String entityId) {
        if (entityId == null || entityId.isEmpty())
            throw new EntityNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_task WHERE user_id = :user_id AND id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("id", entityId);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_task WHERE user_id = :user_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
    }

    @NotNull
    @Override
    public Collection<Task> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<Task> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_task WHERE user_id = :user_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            throw new SQLQueryIsInvalidException();
        }
        return list;
    }

    @NotNull
    private Task fetch(@NotNull final ResultSet row) {
        if (row == null)
            throw new NullPointerException();
        @NotNull final Task task = new Task();
        try {
            task.setId(row.getString(Task.ID));
            task.setUserId(row.getString(Task.USER_ID));
            task.setName(row.getString(Task.NAME));
            task.setDescription(row.getString(Task.DESCRIPTION));
            task.setStartDate(row.getDate(Task.START_DATE));
            task.setFinishDate(row.getDate(Task.FINISH_DATE));
        } catch (SQLException e) {
            throw new SQLQueryIsInvalidException();
        }
        return task;
    }
}
