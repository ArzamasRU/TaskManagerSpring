package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository {

    void removeTaskByName(@Nullable String userId,
                          @Nullable String name);

    void removeTask(@Nullable String userId,
                    @Nullable String id);

    void removeProjectTasks(@Nullable String userId,
                            @Nullable String projectId);

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

    void removeAll(@Nullable String userId);

    @Nullable Task findEntityByName(@Nullable String userId,
                                    @Nullable String name);
}
