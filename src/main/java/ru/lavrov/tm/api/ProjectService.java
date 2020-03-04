package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface ProjectService<T> extends Service<T>{
    void createByName(String projectName, String userId);
    void removeProjectByName(String projectName, String userId);
    Collection<Task> getProjectTasks(String projectName, String userId);
    void renameProject(String oldName, String newName, String userId);
}
