package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Task;

public interface ITaskService extends IService<Task> {
    void createByName(String taskName, String projectName, String userId);
    void removeTaskByName(String taskName, String userId);
    void renameTask(String projectName, String oldName, String newName, String userId);
}
