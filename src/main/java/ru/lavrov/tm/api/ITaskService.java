package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

public interface ITaskService extends IService<Task> {
    void createByName(@Nullable String taskName, @Nullable String projectName, @Nullable String userId);
    void removeTaskByName(@Nullable String taskName, @Nullable String userId);
    void renameTask(@Nullable String projectName, @Nullable String oldName, @Nullable String newName,
                    @Nullable String userId);
}
