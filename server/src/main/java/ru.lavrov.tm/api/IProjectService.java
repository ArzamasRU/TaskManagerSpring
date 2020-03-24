package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectService extends IService<Project> {
    void createByProjectName(@Nullable String userId, @Nullable String projectName);

    void removeProjectByName(@Nullable String userId, @Nullable String projectName);

    Collection<Task> getProjectTasks(@Nullable String userId, @Nullable String projectName);

    void renameProject(@Nullable String userId, @Nullable String oldName, @Nullable String newName);

    Collection<Project> findAll(@Nullable String userId, @Nullable Comparator<Project> comparator);

    Collection<Project> findAllByNamePart(@Nullable String userId, @Nullable String name);

    Collection<Project> findAllByDescPart(@Nullable String userId, @Nullable String description);
}
