package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sql2o.tools.NamedParameterStatement;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Status;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

public final class ProjectRepositoryImpl extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepositoryImpl(@NotNull final Connection connection) {
        super(connection);
    }

    @Override
    public void renameProject(
            @Nullable final String userId, @Nullable final String oldName, @Nullable final String newName
    ) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project project = findEntityByName(userId, newName);
        if (project != null)
            throw new ProjectNameExistsException();
        project = findEntityByName(userId, oldName);
        if (project != null)
            throw new ProjectNameExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        project.setName(newName);
    }

    public void merge(@NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        @NotNull final String query = "UPDATE app_project SET user_id = :user_id, name = :name, " +
                "description = :description, dateBegin = :dateBegin, dateEnd = :dateEnd, WHERE id = :id ";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", project.getId());
            namedParameterStatement.setString("user_id", project.getUserId());
            namedParameterStatement.setString("name", project.getName());
            namedParameterStatement.setString("description", project.getDescription());
            @Nullable final Date startDate = project.getStartDate();
            @Nullable final Date finishDate = project.getFinishDate();
            namedParameterStatement
                    .setDate("dateBegin", startDate == null ? null : new java.sql.Date(startDate.getTime()));
            namedParameterStatement
                    .setDate("dateEnd", finishDate == null ? null : new java.sql.Date(finishDate.getTime()));
//            namedParameterStatement.setString("status", project.getStatus().getStatus());
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public Collection<Project> findAll(@Nullable final String userId, @Nullable final Comparator<Project> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<Project> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_project WHERE id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next())
                list.add(fetch(resultSet));
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (comparator == null)
            return list;
        ((ArrayList<Project>) list).sort(comparator);
        return list;
    }

    @NotNull
    @Override
    public Collection<Project> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @NotNull final Collection<Project> list = new ArrayList<>();
        @NotNull final String query = "SELECT FROM app_project " +
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
            e.printStackTrace();
        }
        return list;
    }

    @NotNull
    @Override
    public Collection<Project> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @NotNull final Collection<Project> list = new ArrayList<>();
        @NotNull final String query = "SELECT FROM app_project WHERE user_id = :user_id AND name LIKE :name";
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
            e.printStackTrace();
        }
        return list;
    }

    @Nullable
    @Override
    public Project findEntityByName(@Nullable final String userId, @Nullable final String entityName) {
        if (entityName == null || entityName.isEmpty())
            throw new EntityNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project project = null;
        @NotNull final String query = "SELECT FROM app_project WHERE user_id = :user_id AND name = :name";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("name", entityName);
            namedParameterStatement.setString("user_id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                project = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project;
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project project = null;
        @NotNull final String query = "SELECT * FROM app_project WHERE user_id = :user_id AND id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.setString("id", projectId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                project = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project;
    }

    public void persist(@NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        @NotNull final String query =
                "INSERT INTO app_project (id, user_id, name, description, dateBegin, dateEnd) " +
                        "VALUES (:id, :user_id, :name, :description, :dateBegin, :dateEnd)";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", project.getId());
            namedParameterStatement.setString("user_id", project.getUserId());
            namedParameterStatement.setString("name", project.getName());
            namedParameterStatement.setString("description", project.getDescription());
            @Nullable final Date startDate = project.getStartDate();
            @Nullable final Date finishDate = project.getFinishDate();
            namedParameterStatement
                    .setDate("dateBegin", startDate == null ? null : new java.sql.Date(startDate.getTime()));
            namedParameterStatement
                    .setDate("dateEnd", finishDate == null ? null : new java.sql.Date(finishDate.getTime()));
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void merge(@NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        @NotNull final String query = "UPDATE app_project SET user_id = :user_id, name = :name, " +
                "description = :description, dateBegin = :dateBegin, dateEnd = :dateEnd, WHERE id = :id ";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", project.getId());
            namedParameterStatement.setString("user_id", project.getUserId());
            namedParameterStatement.setString("name", project.getName());
            namedParameterStatement.setString("description", project.getDescription());
            @Nullable final Date startDate = project.getStartDate();
            @Nullable final Date finishDate = project.getFinishDate();
            namedParameterStatement
                    .setDate("dateBegin", startDate == null ? null : new java.sql.Date(startDate.getTime()));
            namedParameterStatement
                    .setDate("dateEnd", finishDate == null ? null : new java.sql.Date(finishDate.getTime()));
//            namedParameterStatement.setString("status", project.getStatus().getStatus());
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_project WHERE user_id = :user_id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("user_id", userId);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public Collection<Project> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<Project> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_project WHERE user_id = :user_id";
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
            e.printStackTrace();
        }
        return list;
    }

    @NotNull
    private Project fetch(@NotNull final ResultSet row) {
        if (row == null)
            throw new NullPointerException();
        @NotNull final Project project = new Project();
        try {
            project.setId(row.getString(Project.ID));
            project.setUserId(row.getString(Project.USER_ID));
            project.setName(row.getString(Project.NAME));
            project.setDescription(row.getString(Project.DESCRIPTION));
            project.setStartDate(row.getDate(Project.START_DATE));
            project.setFinishDate(row.getDate(Project.FINISH_DATE));
            project.setStatus(Status.getByStatus(row.getString(Project.STATUS)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}
