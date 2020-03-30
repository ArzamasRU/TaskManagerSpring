package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskRepository {
    void removeTaskByName(@Nullable String userId, @Nullable String taskName);

    void removeTask(@Nullable String userId, @Nullable String taskId);

    Collection<Task> getProjectTasks(@Nullable String userId, @Nullable String projectId);

    void removeProjectTasks(@Nullable String userId, @Nullable String projectId);

    Task findProjectTaskByName(@Nullable String userId, @Nullable String taskName, @Nullable String projectId);

    void renameTask(
            @Nullable String userId, @Nullable String projectId, @Nullable String oldName, @Nullable String newName
    );

    Collection<Task> findAll(@Nullable String userId, @Nullable Comparator<Task> comparator);

    Collection<Task> findAllByNamePart(@Nullable String userId, @Nullable String name);

    Collection<Task> findAllByDescPart(@Nullable String userId, @Nullable String description);

    Task findOne(@NotNull String userId, @NotNull String taskId);

    void persist(@Nullable Task entity);

    void merge(@Nullable Task entity);

    void removeAll(@Nullable String userId);

    Collection<Task> findAll(@Nullable String userId);
}
