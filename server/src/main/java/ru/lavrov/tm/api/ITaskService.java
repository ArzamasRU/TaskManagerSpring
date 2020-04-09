package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskService extends IService {
    void createByTaskName(@NotNull String userId, @NotNull String taskName, @NotNull String projectName);

    void removeTaskByName(@NotNull String userId, @NotNull String taskName);

    void removeTask(@NotNull String userId, @NotNull String taskId);

    void renameTask(
            @NotNull String userId, @NotNull String projectName, @NotNull String oldName, @NotNull String newName
    );

    @Nullable Collection<Task> findAll(@NotNull String userId, @NotNull Comparator<Task> comparator);

    @Nullable Collection<Task> findAllByNamePart(@NotNull String userId, @NotNull String name);

    @Nullable Collection<Task> findAllByDescPart(@NotNull String userId, @NotNull String description);

    void persist(@NotNull Task entity);

    void merge(@NotNull Task entity);

    void removeAll(@NotNull String userId);

    @Nullable Collection<Task> findAll(@NotNull String userId);

    @Nullable Task findTaskByName(@NotNull String userId, @NotNull final String taskName);
}
