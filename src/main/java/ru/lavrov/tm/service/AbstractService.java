package ru.lavrov.tm.service;

import ru.lavrov.tm.api.*;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.AbstractRepository;

import java.util.Collection;

public abstract class AbstractService<T extends IEntity> implements IService<T> {
    protected final IRepository repository;

    public AbstractService(final IRepository abstractRepository) {
        this.repository = abstractRepository;
    }

    @Override
    public void persist(final T entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        repository.persist(entity);
    }

    @Override
    public void merge(final T entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        repository.persist(entity);
    }

    @Override
    public void remove(final String projectId, final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNotExistsException();
        repository.remove(projectId, userId);
    }

    @Override
    public void removeAll(final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        repository.removeAll(userId);
    }

    @Override
    public Collection<T> findAll(final String userId){
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        return repository.findAll(userId);
    }
}
