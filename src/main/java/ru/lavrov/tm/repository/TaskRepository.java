package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {
    Map<String, Task> tasks = new HashMap();

    public List<Task> findAll(){
        return new ArrayList(tasks.values());
    }

    public Task FindOne(String id){
        return tasks.get(id);
    }

    public void persist(Task task) throws Exception {
        String id = task.getId();
        if (tasks.containsKey(id))
            throw new Exception("project already exists!");
        else
            tasks.put(id, task);
    }

    public void merge(Task task){
        tasks.put(task.getId(), task);
    }

    public void remove(String id){
        tasks.remove(id);
    }

    public void removeAll(){
        tasks.clear();
    }
}
