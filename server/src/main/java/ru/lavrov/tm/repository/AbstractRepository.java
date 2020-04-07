package ru.lavrov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;

@NoArgsConstructor
public abstract class AbstractRepository {

    @NotNull
    protected EntityManager entityManager;

    @NotNull
    public AbstractRepository(@NotNull EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
