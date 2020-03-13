package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.api.IService;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Collection;

public abstract class AbstractService<T extends IEntity> implements IService<T> {
    @NotNull
    protected final IRepository repository;

    public AbstractService(final IRepository abstractRepository) {
        this.repository = abstractRepository;
    }

    @Override
    public void persist(@Nullable final T entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        repository.persist(entity);
    }

    @Override
    public void merge(@Nullable final T entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        repository.persist(entity);
    }

    @Override
    public void remove(@Nullable final String entityId, @Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (entityId == null || entityId.isEmpty())
            throw new ProjectNotExistsException();
        repository.remove(entityId, userId);
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        repository.removeAll(userId);
    }

    @Nullable
    @Override
    public Collection<T> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        return repository.findAll(userId);
    }
}
