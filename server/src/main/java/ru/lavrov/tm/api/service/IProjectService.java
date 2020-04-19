package ru.lavrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectService {
    void createByProjectName(@NotNull final String userId, @NotNull final String name);

    void createProject(@NotNull final String userId, @NotNull final Project project);

    void removeProjectByName(@NotNull String userId, @NotNull String projectName);

    @Nullable Collection<Task> getProjectTasks(@NotNull String userId, @NotNull String projectName);

    void renameProject(@NotNull String userId, @NotNull String oldName, @NotNull String newName);

    @Nullable Collection<Project> findAll(@NotNull String userId, @NotNull Comparator<Project> comparator);

    @Nullable Collection<Project> findAllByNamePart(@NotNull String userId, @NotNull String name);

    @Nullable Collection<Project> findAllByDescPart(@NotNull String userId, @NotNull String description);

    void removeProject(@NotNull final String userId, @NotNull final String entityId);

    void persist(@NotNull Project entity);

    void merge(@NotNull Project entity);

    void removeAll(@NotNull String userId);

    @Nullable Collection<Project> findAll(@NotNull String userId);

    @Nullable Project findOne(@NotNull String userId, @NotNull String entityId);

    @Nullable Project findProjectByName(@NotNull final String userId, @NotNull final String projectName);
}
