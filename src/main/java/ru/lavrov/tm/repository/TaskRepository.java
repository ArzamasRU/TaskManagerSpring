package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.TaskExistsException;

import java.util.*;

public class TaskRepository {
    private Map<String, Task> tasks = new HashMap();

    public Collection<Task> findAll(){
        return tasks.values();
    }

    public Task FindOne(String id){
        return tasks.get(id);
    }

    public void persist(Task task) throws RuntimeException {
        String id = task.getId();
        if (tasks.containsKey(id))
            throw new TaskExistsException();
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

    public Collection<Task> getProjectTasks(Project project){
        List<Task> list = new ArrayList();
        for (Task task : findAll()) {
            if (task.getProjectId().equals(project.getId())) {
                list.add(task);
            }
        }
        return list;
    }

    public void removeProjectTasks(Project project){
        for (Task task : findAll()) {
            if (task.getProjectId().equals(project.getId())) {
                tasks.remove(task.getId());
            }
        }
    }

    public Task findTaskByName(String name){
        Task currentTask = null;
        for (Task task: findAll()) {
            currentTask = task;
            if (name.equals(task.getName()))
                break;
        }
        return currentTask;
    }

    public void attachTask(Task task, Project project){
        tasks.get(task.getId()).setProjectId(project.getId());
//        task.setProjectId(project.getId());
    }

    public void renameTask(String oldName, String newName) throws RuntimeException {
        findTaskByName(oldName).setName(newName);
    }
}
