package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository {
    Map<String, Client> clients = new HashMap();

    public List<Client> findAll(){
        return new ArrayList(clients.values());
    }

    public Client FindOne(String login){
        return clients.get(login);
    }

    public void persist(Client client) throws Exception {
        String login = client.getLogin();
        if (clients.containsKey(login))
            throw new Exception("project already exists!");
        else
            clients.put(login, client);
    }

    public void merge(Client client){
        clients.put(client.getLogin(), client);
    }

    public void remove(String login){
        clients.remove(login);
    }

    public void removeAll(){
        clients.clear();
    }
}
