package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;
import java.util.Comparator;

public interface IService<T extends IEntity> {
    void persist(@Nullable T entity);
    void merge(@Nullable T entity);
    void remove(@Nullable String entityId, @Nullable String userId);
    void removeAll(@Nullable String userId);
    Collection<T> findAll(@Nullable String userId);
    Collection<T> findAll(@Nullable String userId, @Nullable Comparator<T> comparator);
}
