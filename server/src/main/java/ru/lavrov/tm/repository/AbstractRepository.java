package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IRepository;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    @NotNull
    protected final Connection connection;

    @NotNull
    protected final Map<String, T> entities = new LinkedHashMap<>();

    public AbstractRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }
}
