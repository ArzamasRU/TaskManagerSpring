package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IRepository;
import ru.lavrov.tm.exception.entity.EntityExistsException;
import ru.lavrov.tm.exception.entity.EntityNotExistsException;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
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
