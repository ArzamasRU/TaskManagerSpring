package ru.lavrov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

public final class UserRepositoryImpl extends AbstractRepository<User> implements IUserRepository {
    public void updatePassword(@Nullable final String userId, @Nullable final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        entities.get(userId).setPassword(newPassword);
    }

    public void updateLogin(@Nullable final String userId, @Nullable final String login) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        entities.get(userId).setLogin(login);
    }

    @Nullable
    public User findUserByLogin(@Nullable final String login){
        if (login == null || login.isEmpty())
            throw new EntityNameIsInvalidException();
        @Nullable User currentUser = null;
        for (@Nullable final User user : entities.values()) {
            if (user == null)
                continue;
            if (login.equals(user.getName())) {
                currentUser = user;
                break;
            }
        }
        return currentUser;
    }
}
