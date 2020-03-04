package ru.lavrov.tm.api;

public interface TaskService<Task> extends Service<Task>{
    void createByName(String taskName, String projectName, String userId);
    void removeTaskByName(String taskName, String userId);
    void renameTask(String projectName, String oldName, String newName, String userId);
}
