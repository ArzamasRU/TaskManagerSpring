package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface IProjectService extends IService<Project> {
    void createByName(@Nullable String projectName, @Nullable String userId);
    void removeProjectByName(@Nullable String projectName, @Nullable String userId);
    Collection<Task> getProjectTasks(@Nullable String projectName, @Nullable String userId);
    void renameProject(@Nullable String oldName, @Nullable String newName, @Nullable String userId);
}
