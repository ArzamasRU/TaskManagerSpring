package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.dto.TaskDTO;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskService extends IService {
    void createByTaskName(@Nullable String userId, @NotNull String taskName, @NotNull String projectName);

    void removeTaskByName(@Nullable String userId, @Nullable String taskName);

    void removeTask(@Nullable String userId, @Nullable String taskId);

    void renameTask(
            @Nullable String userId, @Nullable String projectName, @Nullable String oldName, @Nullable String newName
    );

    Collection<TaskDTO> findAll(@Nullable String userId, @Nullable Comparator<TaskDTO> comparator);

    Collection<TaskDTO> findAllByNamePart(@Nullable String userId, @Nullable String name);

    Collection<TaskDTO> findAllByDescPart(@Nullable String userId, @Nullable String description);

    void persist(@Nullable TaskDTO entity);

    void merge(@Nullable TaskDTO entity);

    void removeAll(@Nullable String userId);

    Collection<TaskDTO> findAll(@Nullable String userId);

    TaskDTO findTaskByName(@Nullable String userId, @Nullable final String taskName);
}
