package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public interface IRepository<T extends IEntity> {
    void persist(@Nullable T entity);

    void merge(@Nullable T entity);

    void remove(@Nullable String userId, @Nullable String entityId);

    void removeAll(@Nullable String userId);

    Collection<T> findAll(@Nullable String userId);
}

