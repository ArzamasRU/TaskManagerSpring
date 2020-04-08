package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectService extends IService {
    void createByProjectName(@Nullable Project project);

    void removeProjectByName(@Nullable String userId, @Nullable String projectName);

    @Nullable Collection<Task> getProjectTasks(@Nullable String userId, @Nullable String projectName);

    void renameProject(@Nullable String userId, @Nullable String oldName, @Nullable String newName);

    @Nullable Collection<Project> findAll(@Nullable String userId, @Nullable Comparator<Project> comparator);

    @Nullable Collection<Project> findAllByNamePart(@Nullable String userId, @Nullable String name);

    @Nullable Collection<Project> findAllByDescPart(@Nullable String userId, @Nullable String description);

    void removeProject(@Nullable final String userId, @Nullable final String entityId);

    void persist(@Nullable Project entity);

    void merge(@Nullable Project entity);

    void removeAll(@Nullable String userId);

    @Nullable Collection<Project> findAll(@Nullable String userId);

    @Nullable Project findOne(@Nullable String userId, @Nullable String entityId);

    @Nullable Project findProjectByName(@Nullable final String userId, @Nullable final String projectName);
}
