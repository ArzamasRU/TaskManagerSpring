package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.lavrov.tm.api.IEntity;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractRepository {

    @NotNull
    protected final Connection connection;

    public AbstractRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }
}
