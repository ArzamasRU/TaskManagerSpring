package ru.lavrov.tm.service;


import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.repository.UserRepository;
import ru.lavrov.tm.role.Role;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void persist(String login, String password, String role) {
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        Role currentRole = Role.valueOf(role);
        if (currentRole == null)
            throw new UserRoleIsInvalidException();
        User user = userRepository.findUserByLogin(login);
        if (user != null)
            throw new UserLoginExistsException();
        userRepository.persist(new User(login, password, currentRole));
    }

    public void updatePassword(String userId, String newPassword) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        userRepository.updatePassword(userId, newPassword);
    }

    public void updateLogin(String userId, String newLogin) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        User user = userRepository.findUserByLogin(newLogin);
        if (user != null)
            throw new UserLoginExistsException();
        userRepository.updateLogin(userId, newLogin);
    }
}
