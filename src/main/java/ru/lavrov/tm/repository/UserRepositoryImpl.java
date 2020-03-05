package ru.lavrov.tm.repository;

import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

public final class UserRepositoryImpl extends AbstractRepository<User> implements IUserRepository {
    public void updatePassword(final String userId, final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        entities.get(userId).setPassword(newPassword);
    }

    public void updateLogin(final String userId, final String login) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        entities.get(userId).setLogin(login);
    }
}
