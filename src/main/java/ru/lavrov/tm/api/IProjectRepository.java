package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Project;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository extends IRepository<Project> {
    void renameProject(@Nullable String oldName, @Nullable String newName, @Nullable String userId);
    Project findEntityByName(@Nullable String entityName, @Nullable String userId);
//    Collection<Project> findAll(@Nullable String userId, @Nullable Comparator<Project> comparator);
}
