package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.task.TaskExistsException;

import java.util.*;

public class TaskRepository {
    private Map<String, Task> tasks = new HashMap();

    public Collection<Task> findAll(){
        return tasks.values();
    }

    public Collection<Task> findAllByUser(String userId) {
        Collection<Task> list = new ArrayList<>();
        for (Task task : findAll()) {
            if (task.getUserId().equals(userId))
                list.add(task);
        }
        return list;
    }

    public Task findOne(String id){
        return tasks.get(id);
    }

    public void persist(Task task) throws RuntimeException {
        String id = task.getId();
        if (tasks.containsKey(id))
            throw new TaskExistsException();
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

    public Collection<Task> getProjectTasks(String projectId){
        List<Task> list = new ArrayList();
        for (Task task : findAll()) {
            if (task.getProjectId().equals(projectId)) {
                list.add(task);
            }
        }
        return list;
    }

//    public Collection<Task> getProjectTasksByUser(Project project, User sessionUser){
//        List<Task> list = new ArrayList();
//        for (Task task : findAll()) {
//            if (task.getUserId().equals(sessionUser) && task.getProjectId().equals(project.getId())) {
//                list.add(task);
//            }
//        }
//        return list;
//    }

    public void removeProjectTasks(String projectId){
        for (Task task : findAll()) {
            if (task.getProjectId().equals(projectId)) {
                tasks.remove(task.getId());
            }
        }
    }

    public Task findTaskByName(String name){
        Task currentTask = null;
        for (Task task: findAll()) {
            if (task.getName().equals(name)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public Task findProjectTaskByName(String name, String projectId, String userId){
        Task currentTask = null;
        for (Task task: findAll()) {
            if (task.getName().equals(name) && task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public void renameTask(String taskId, String newName) throws RuntimeException {
        findOne(taskId).setName(newName);
    }
}
