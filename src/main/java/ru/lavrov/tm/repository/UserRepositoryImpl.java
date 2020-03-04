package ru.lavrov.tm.repository;

import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

public final class UserRepositoryImpl extends AbstractUserRepository {
    public void updatePassword(final String userId, final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        users.get(userId).setPassword(newPassword);
    }

    public void updateLogin(final String userId, final String login) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        users.get(userId).setLogin(login);
    }
}
