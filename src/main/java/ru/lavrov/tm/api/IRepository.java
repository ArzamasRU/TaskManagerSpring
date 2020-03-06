package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IRepository<T extends IEntity> {
    void persist(@Nullable T entity);
    void merge(@Nullable T entity);
    void remove(@Nullable String entityId, @Nullable String userId);
    void removeAll(@Nullable String userId);
    Collection<T> findAll(@Nullable String userId);
    T findEntityByName(@Nullable String entityName, @Nullable String userId);
}
