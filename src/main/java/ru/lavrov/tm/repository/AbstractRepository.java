package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.entity.EntityExistsException;
import ru.lavrov.tm.exception.entity.EntityNameIsInvalidException;
import ru.lavrov.tm.exception.entity.EntityNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {
    @NotNull
    protected final Map<String, T> entities = new HashMap();

    @Override
    public void persist(@Nullable final T entity) {
        if (entity == null)
            throw new EntityNotExistsException();
        final String id = entity.getId();
        if (entities.containsKey(id))
            throw new EntityExistsException();
        entities.put(id, entity);
    }

    @Override
    public void merge(@Nullable final T entity){
        if (entity == null)
            throw new EntityNotExistsException();
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(@Nullable final String entityId, @Nullable final String userId){
        if (entityId == null || entityId.isEmpty())
            throw new EntityNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final T entity = entities.get(entityId);
        if (!entity.getUserId().equals(userId))
            throw new EntityNotExistsException();
        entities.remove(entityId);
    }

    @Override
    public void removeAll(@Nullable final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Collection<T> list = findAll(userId);
        if (list == null)
            return;
        for (@Nullable final T entity : list) {
            if (entity == null)
                continue;
            remove(entity.getId(), userId);
        }
    }

    @Nullable
    @Override
    public T findEntityByName(@Nullable final String entityName, @Nullable final String userId){
        if (entityName == null || entityName.isEmpty())
            throw new EntityNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        T currentEntity = null;
        for (@Nullable final T entity : entities.values()) {
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

    @Nullable
    @Override
    public Collection<T> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        final Collection<T> list = new ArrayList<>();
        for (@Nullable final T entity : entities.values()) {
            if (entity == null)
                continue;
            if (entity.getUserId().equals(userId))
                list.add(entity);
        }
        return list;
    }
}
