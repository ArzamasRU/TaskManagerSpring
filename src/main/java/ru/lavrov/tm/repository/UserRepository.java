package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
import ru.lavrov.tm.exception.user.UserPasswordIsInvalidException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> users = new HashMap();

//    public List<User> findAll(){
//        return new ArrayList(users.values());
//    }

//    public User findOne(String id){
//        return users.get(id);
//    }

    public void persist(User user) {
        String id = user.getId();
        if (users.containsKey(id))
            throw new UserExistsException();
        users.put(id, user);
    }

    public void merge(User user){
        users.put(user.getLogin(), user);
    }

//    public void remove(String login){
//        users.remove(login);
//    }
//
//    public void removeAll(){
//        users.clear();
//    }

    public User findUserByLogin(String login){
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        User currentUser = null;
        for (User user: users.values()) {
            if (login.equals(user.getLogin())) {
                currentUser = user;
                break;
            }
        }
        return currentUser;
    }

    public void updatePassword(String userId, String newPassword) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (newPassword == null || newPassword.isEmpty())
            throw new UserPasswordIsInvalidException();
        users.get(userId).setPassword(newPassword);
    }

    public void updateLogin(String userId, String login) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        users.get(userId).setLogin(login);
    }
}
