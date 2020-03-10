package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskService extends IService<Task> {
    void createByName(@Nullable String taskName, @Nullable String projectName, @Nullable String userId);
    void removeTaskByName(@Nullable String taskName, @Nullable String userId);
    void renameTask(@Nullable String projectName, @Nullable String oldName, @Nullable String newName,
                    @Nullable String userId);
//    Collection<Task> findAll(@Nullable String userId, @Nullable Comparator<Task> comparator);
}
