package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.userException.UserLoginExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> clients = new HashMap();

    public List<User> findAll(){
        return new ArrayList(clients.values());
    }

    public User FindOne(String login){
        return clients.get(login);
    }

    public void persist(User user) throws Exception {
        String login = user.getLogin();
        if (clients.containsKey(login))
            throw new UserLoginExistsException();
        clients.put(login, user);
    }

    public void merge(User user){
        clients.put(user.getLogin(), user);
    }

    public void remove(String login){
        clients.remove(login);
    }

    public void removeAll(){
        clients.clear();
    }
}
