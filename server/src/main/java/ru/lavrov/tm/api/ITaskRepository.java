package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository {

    void removeTask(@Nullable Task task);

    void removeAll(@Nullable Collection<Task> taskList);

    @Nullable Collection<Task> getProjectTasks(@Nullable String userId,
                                               @Nullable String projectId);

    @Nullable Task findProjectTaskByName(@Nullable String userId,
                                         @Nullable String name,
                                         @Nullable String projectId);

    void renameTask(@Nullable String userId,
                    @Nullable String projectId,
                    @Nullable String oldName,
                    @Nullable String newName);

    @Nullable Collection<Task> findAll(@Nullable String userId);

    @Nullable Collection<Task> findAllByNamePart(@Nullable String userId,
                                                 @Nullable String pattern);

    @Nullable Collection<Task> findAllByDescPart(@Nullable String userId,
                                                 @Nullable String pattern);

    @Nullable Task findOne(@NotNull String userId,
                           @NotNull String id);

    void persist(@Nullable Task entity);

    void merge(@Nullable Task entity);

    @Nullable Task findEntityByName(@Nullable String userId,
                                    @Nullable String name);
}
