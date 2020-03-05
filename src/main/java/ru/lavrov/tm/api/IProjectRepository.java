package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.Project;

public interface IProjectRepository extends IRepository<Project> {
    void renameProject(String oldName, String newName, String userId);
}
