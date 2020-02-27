package ru.lavrov.tm.service;


import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.userException.*;
import ru.lavrov.tm.repository.UserRepository;
import ru.lavrov.tm.role.Role;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public void login(String login, String password){
//        if (login == null || login.isEmpty())
//            throw new UserLoginIsInvalidException();
//        if (password == null || password.isEmpty())
//            throw new UserPasswordIsInvalidException();
//        User user = userRepository.findUserByLogin(login);
//        if (user == null)
//            throw new UserLoginNotExistsException();
//        if (password.equals(user.getPassword()))
//            throw new UserLoginOrPasswordIsIncorrectException();
//        userRepository.login(user);
//    }

//    public void logout(){
//        User user = userRepository.findAuthorizedUser();
////        if (user == null)
////            throw new UserIsNotAuthorizedException();
//        userRepository.logout(user);
//    }

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

    public void changePassword(User sessionUser,String newPassword) {
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        sessionUser.setPassword(newPassword);
    }

    public void changeLogin(User sessionUser, String newLogin) {
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        if (newLogin == null || newLogin.isEmpty())
            throw new UserLoginIsInvalidException();
        User user = userRepository.findUserByLogin(newLogin);
        if (user != null)
            throw new UserLoginExistsException();
        sessionUser.setLogin(newLogin);
    }

//    public User findAuthorizedUser(){
//        return userRepository.findAuthorizedUser();
//    }
}
