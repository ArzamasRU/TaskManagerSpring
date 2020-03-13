package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskRepository extends IRepository<Task> {
    void removeTaskByName(@Nullable String taskName, @Nullable String userId);

    Collection<Task> getProjectTasks(@Nullable String projectId, @Nullable String userId);

    void removeProjectTasks(@Nullable String projectId, @Nullable String userId);

    Task findProjectTaskByName(@Nullable String taskName, @Nullable String projectId, @Nullable String userId);

    void renameTask(@Nullable String projectId, @Nullable String oldName, @Nullable String newName, @Nullable String userId);

    Task findEntityByName(@Nullable String entityName, @Nullable String userId);

    Collection<Task> findAll(@Nullable String userId, @Nullable Comparator<Task> comparator);

    Collection<Task> findAllByNamePart(@Nullable String name, @Nullable String userId);

    Collection<Task> findAllByDescPart(@Nullable String description, @Nullable String userId);
}
