package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

public final class ProjectRepositoryImpl extends AbstractProjectRepository {
    public void renameProject(final String oldName, final String newName, final String userId) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        Project project = findEntityByName(newName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        project = findEntityByName(oldName, userId);
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        project.setName(newName);
    }
}
