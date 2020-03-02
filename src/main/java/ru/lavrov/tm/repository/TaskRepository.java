package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.*;

public class TaskRepository {
    private Map<String, Task> tasks = new HashMap();

//    public Collection<Task> findAll(){
//        return tasks.values();
//    }

    public Collection<Task> findAllByUser(String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Collection<Task> list = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId))
                list.add(task);
        }
        return list;
    }

//    public Task findOne(String id){
//        return tasks.get(id);
//    }

    public void persist(Task task) throws RuntimeException {
        String id = task.getId();
        if (tasks.containsKey(id))
            throw new TaskExistsException();
        tasks.put(id, task);
    }

    public void merge(Task task){
        tasks.put(task.getId(), task);
    }

    public void remove(String taskId, String userId){
        if (taskId == null)
            throw new TaskNotExistsException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task task = tasks.get(taskId);
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        tasks.remove(taskId);
    }

    public void removeTaskByName(String taskName, String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task task = findTaskByName(taskName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        tasks.remove(task.getId());
    }

    public void removeAll(String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        for (Task task : findAllByUser(userId)) {
            remove(task.getId(), userId);
        }
    }

    public Collection<Task> getProjectTasks(String projectId, String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        List<Task> list = new ArrayList();
        for (Task task : tasks.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
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

    public void removeProjectTasks(String projectId, String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        for (Task task : tasks.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                tasks.remove(task.getId());
            }
        }
    }

    public Task findTaskByName(String name, String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task currentTask = null;
        for (Task task: tasks.values()) {
            if (task.getName().equals(name) && task.getUserId().equals(userId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public Task findProjectTaskByName(String taskName, String projectId, String userId){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task currentTask = null;
        for (Task task: tasks.values()) {
            if (task.getName().equals(taskName) && task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public void renameTask(String projectId, String oldName, String newName, String userId) throws RuntimeException {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();

        Task task = findTaskByName(oldName, userId);
        if (task == null)
            throw new TaskNotExistsException();
        if (task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        if (findProjectTaskByName(newName, projectId, userId) != null)
            throw new TaskExistsException();
        task.setName(newName);
    }
}
