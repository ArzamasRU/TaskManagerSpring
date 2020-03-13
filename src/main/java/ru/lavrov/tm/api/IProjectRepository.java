package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository extends IRepository<Project> {
    void renameProject(@Nullable String oldName, @Nullable String newName, @Nullable String userId);

    Project findEntityByName(@Nullable String entityName, @Nullable String userId);

    Collection<Project> findAll(@Nullable String userId, @Nullable Comparator<Project> comparator);

    Collection<Project> findAllByNamePart(@Nullable String name, @Nullable String userId);

    Collection<Project> findAllByDescPart(@Nullable String description, @Nullable String userId);
}
