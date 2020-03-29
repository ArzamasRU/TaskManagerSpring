package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IService<T extends IEntity> {
    void persist(@Nullable T entity);

    void merge(@Nullable T entity);

    void removeAll(@Nullable String userId);

    Collection<T> findAll(@Nullable String userId);
}
