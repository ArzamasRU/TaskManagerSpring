package ru.lavrov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public final class ProjectRepositoryImpl extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public void renameProject(
            @Nullable final String oldName, @Nullable final String newName, @Nullable final String userId
    ) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project project = findEntityByName(newName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        project = findEntityByName(oldName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        project.setName(newName);
    }

    @Nullable
    @Override
    public Collection<Project> findAll(@Nullable final String userId, @Nullable final Comparator<Project> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Collection<Project> list = new ArrayList<>();
        for (@Nullable final Project entity : entities.values()) {
            if (entity == null)
                continue;
            if (entity.getUserId().equals(userId))
                list.add(entity);
        }
        if (comparator == null)
            return list;
        ((ArrayList<Project>) list).sort(comparator);
        return list;
    }

    @Nullable
    @Override
    public Collection<Project> findAllByNamePart(@Nullable final String name, @Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Collection<Project> list = new ArrayList<>();
        for (@Nullable final Project entity : entities.values()) {
            if (entity == null)
                continue;
            if (entity.getUserId().equals(userId) && entity.getName().contains(name))
                list.add(entity);
        }
        return list;
    }

    @Nullable
    @Override
    public Collection<Project> findAllByDescPart(@Nullable final String description, @Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @Nullable final Collection<Project> list = new ArrayList<>();
        for (@Nullable final Project entity : entities.values()) {
            if (entity == null)
                continue;
            if (entity.getUserId().equals(userId) && entity.getDescription().contains(description))
                list.add(entity);
        }
        return list;
    }

    @Nullable
    @Override
    public Project findEntityByName(@Nullable final String entityName, @Nullable final String userId) {
        if (entityName == null || entityName.isEmpty())
            throw new EntityNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Project currentEntity = null;
        for (@Nullable final Project entity : entities.values()) {
            if (entity == null)
                continue;
            boolean isEntityNameEquals = entityName.equals(entity.getName());
            boolean isEntityUserIdEquals = entity.getUserId().equals(userId);
            if (isEntityNameEquals && isEntityUserIdEquals) {
                currentEntity = entity;
                break;
            }
        }
        return currentEntity;
    }
}
