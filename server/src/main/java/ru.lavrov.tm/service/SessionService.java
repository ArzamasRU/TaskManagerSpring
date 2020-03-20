package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ISessionService;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.constant.Constant;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserLoginNotExistsException;
import ru.lavrov.tm.exception.user.UserLoginOrPasswordIsIncorrectException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;
import ru.lavrov.tm.util.HashUtil;

import static ru.lavrov.tm.util.SignUtil.getSign;

public final class SessionService implements ISessionService {

    @NotNull
    private final IUserRepository userRepository;

    public SessionService(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean verifySession(@Nullable final Session session){
        if (session == null)
            return false;
        @Nullable final String currentSign = getSign(session, Constant.SALT, Constant.CYCLE);
        if (currentSign == null || currentSign.isEmpty())
            return false;
        if (session.getSign().equals(currentSign))
            return true;
        return false;
    }

    @NotNull
    public Session login(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
//        @Nullable final User user = login(login, password);
        @Nullable final User user = userRepository.findUserByLogin(login);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        @Nullable final Session session =
                new Session(user.getId(), user.getRole(), System.currentTimeMillis(), null);
        session.setSign(getSign(session, Constant.SALT, Constant.CYCLE));
        return session;
    }
}

