package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.dto.TaskDTO;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectService extends IService {
    void createByProjectName(@Nullable String userId, @Nullable String projectName);

    void removeProjectByName(@Nullable String userId, @Nullable String projectName);

    Collection<TaskDTO> getProjectTasks(@Nullable String userId, @Nullable String projectName);

    void renameProject(@Nullable String userId, @Nullable String oldName, @Nullable String newName);

    Collection<ProjectDTO> findAll(@Nullable String userId, @Nullable Comparator<ProjectDTO> comparator);

    Collection<ProjectDTO> findAllByNamePart(@Nullable String userId, @Nullable String name);

    Collection<ProjectDTO> findAllByDescPart(@Nullable String userId, @Nullable String description);

    void removeProject(@Nullable final String userId, @Nullable final String entityId);

    void persist(@Nullable ProjectDTO entity);

    void merge(@Nullable ProjectDTO entity);

    void removeAll(@Nullable String userId);

    Collection<ProjectDTO> findAll(@Nullable String userId);

    ProjectDTO findOne(@Nullable String userId, @Nullable String entityId);

    ProjectDTO findProjectByName(@Nullable final String userId, @Nullable final String projectName);
}
