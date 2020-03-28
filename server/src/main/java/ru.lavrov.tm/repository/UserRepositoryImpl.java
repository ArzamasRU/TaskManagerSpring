package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sql2o.tools.NamedParameterStatement;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserNotExistsException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public final class UserRepositoryImpl extends AbstractRepository<User> implements IUserRepository {

    public UserRepositoryImpl(@NotNull Connection connection) {
        super(connection);
    }

    @Override
    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        @NotNull final String query = "UPDATE app_user SET passwordHash = :passwordHash WHERE id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", userId);
            namedParameterStatement.setString("passwordHash", newPassword);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLogin(@Nullable final String userId, @Nullable final String login) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        @NotNull final String query = "UPDATE app_user SET login = :login WHERE id = :id ";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", userId);
            namedParameterStatement.setString("login", login);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public User findUserByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty())
            throw new EntityNameIsInvalidException();
        @Nullable User user = null;
        @NotNull final String query = "SELECT FROM app_user WHERE login = :login";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("login", login);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                user = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable User user = null;
        @NotNull final String query = "SELECT * FROM app_user WHERE id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", userId);
            @Nullable final ResultSet resultSet = namedParameterStatement.executeQuery();
            if (resultSet.next())
                user = fetch(resultSet);
            resultSet.close();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void persist(@NotNull final User user) {
        if (user == null)
            throw new UserNotExistsException();
        @NotNull final String query = "INSERT INTO app_user (id, login, passwordHash, role) " +
                "VALUES (:id, :login, :passwordHash, :role)";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", user.getId());
            namedParameterStatement.setString("login", user.getLogin());
            namedParameterStatement.setString("passwordHash", user.getPassword());
            namedParameterStatement.setString("role", user.getRole().getRole());
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void merge(@NotNull final User user) {
        if (user == null)
            throw new UserNotExistsException();
        @NotNull final String query = "UPDATE app_user SET " +
                "login = :login, passwordHash = :passwordHash, role = :role WHERE id = :id ";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", user.getId());
            namedParameterStatement.setString("login", user.getLogin());
            namedParameterStatement.setString("passwordHash", user.getPassword());
            namedParameterStatement.setString("role", user.getRole().getRole());
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final String query = "DELETE FROM app_user WHERE id = :id";
        try {
            @Nullable final NamedParameterStatement namedParameterStatement =
                    new NamedParameterStatement(connection, query, false);
            namedParameterStatement.setString("id", userId);
            namedParameterStatement.execute();
            namedParameterStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public Collection<User> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final Collection<User> list = new ArrayList<>();
        @NotNull final String query = "SELECT * FROM app_user WHERE id = :id";
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
        return list;
    }

    @NotNull
    private User fetch(@NotNull final ResultSet row) {
        if (row == null)
            throw new NullPointerException();
        @NotNull final User user = new User();
        try {
            user.setId(row.getString(User.ID));
            user.setLogin(row.getString(User.LOGIN));
            user.setPassword(row.getString(User.PASSWORD_HASH));
            user.setRole(Role.getByRole(row.getString(User.ROLE)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
