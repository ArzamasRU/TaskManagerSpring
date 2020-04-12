package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository {
    void renameProject(@Nullable String userId,
                       @Nullable String oldName,
                       @Nullable String newName);

    @Nullable Project findEntityByName(@Nullable String userId,
                                       @Nullable String name);

    @Nullable Collection<Project> findAll(@Nullable String userId);

    @Nullable Collection<Project> findAllByNamePart(@Nullable String userId,
                                                    @Nullable String pattern);

    @Nullable Collection<Project> findAllByDescPart(@Nullable String userId,
                                                    @Nullable String pattern);

    @Nullable Project findOne(@Nullable String userId,
                              @Nullable String id);

    void removeProject(@Nullable Project project);

    void removeAll(@Nullable Collection<Project> projectList);

    void persist(@Nullable Project project);

    void merge(@Nullable Project entity);
}
