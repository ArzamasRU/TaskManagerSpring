package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Project;
import org.jetbrains.annotations.Nullable;

public interface IProjectRepository extends IRepository<Project> {
    void renameProject(@Nullable String oldName, @Nullable String newName, @Nullable String userId);
}
