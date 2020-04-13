package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.service.IService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.util.ConnectionUtil;

import java.sql.Connection;

public abstract class AbstractService implements IService {

    @NotNull
    protected Bootstrap bootstrap;

    public AbstractService(@NotNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Nullable
    public Connection getConnection() {
        return ConnectionUtil.getConnection();
    }
}
