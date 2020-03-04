package ru.lavrov.tm.service;


import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.role.Role;

public final class UserServiceImpl extends AbstractUserService {
    public UserServiceImpl(final UserRepository userRepository) {
        super(userRepository);
    }

    public void createByLogin(final String login, final String password, final String role) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        Role currentRole = Role.valueOf(role);
        if (currentRole == null)
            throw new UserRoleIsInvalidException();
        User user = (User) userRepository.findEntityByName(login, null);
        if (user != null)
            throw new UserLoginExistsException();
        persist(new User(login, password, currentRole));
    }

    public void updatePassword(final String userId, final String newPassword) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        userRepository.updatePassword(userId, newPassword);
    }

    public void updateLogin(final String userId, final String newLogin) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        User user = (User) userRepository.findEntityByName(newLogin, null);
        if (user != null)
            throw new UserLoginExistsException();
        userRepository.updateLogin(userId, newLogin);
    }

//    public void remove(String userId) {
//        if (userId == null)
//            throw new UserIsNotAuthorizedException();
//        remove(userId);
//    }
}
