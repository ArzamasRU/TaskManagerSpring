package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends IRepository<Task> {
    void removeTaskByName(@Nullable String taskName, @Nullable String userId);
    @Nullable Collection<Task> getProjectTasks(@Nullable String projectId, @Nullable String userId);
    void removeProjectTasks(@Nullable String projectId, @Nullable String userId);
    @Nullable Task findProjectTaskByName(@Nullable String taskName, @Nullable String projectId, @Nullable String userId);
    void renameTask(@Nullable String projectId, @Nullable String oldName, @Nullable String newName, @Nullable String userId);
}
