package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> users = new HashMap();

    public List<User> findAll(){
        return new ArrayList(users.values());
    }

    public User FindOne(String id){
        return users.get(id);
    }

    public void persist(User user) {
        String id = user.getId();
        if (users.containsKey(id))
            throw new UserExistsException();
        users.put(id, user);
    }

    public void merge(User user){
        users.put(user.getLogin(), user);
    }

    public void remove(String login){
        users.remove(login);
    }

    public void removeAll(){
        users.clear();
    }

    public User findUserByLogin(String login){
        User currentUser = null;
        for (User user: findAll()) {
            if (login.equals(user.getLogin())) {
                currentUser = user;
                break;
            }
        }
        return currentUser;
    }
}
