package ru.lavrov.tm.api;

import java.util.Collection;

public interface Service<T> {
    void persist(T entity);
    void merge(T entity);
    void remove(String entityId, String userId);
    void removeAll(String userId);
    Collection<T> findAllByUser(String userId);
}
