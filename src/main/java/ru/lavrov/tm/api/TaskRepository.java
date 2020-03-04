package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface TaskRepository<T> extends Repository<T>{
    void removeTaskByName(String taskName, String userId);
    Collection<Task> getProjectTasks(String projectId, String userId);
    void removeProjectTasks(String projectId, String userId);
    Task findProjectTaskByName(String taskName, String projectId, String userId);
    void renameTask(String projectId, String oldName, String newName, String userId);
}