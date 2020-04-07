package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IService;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.util.ConnectionUtil;

import java.sql.Connection;

public abstract class AbstractService implements IService {

    @NotNull
    protected IServiceLocator bootstrap;

    public AbstractService(@NotNull IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Nullable
    public Connection getConnection() {
        return ConnectionUtil.getConnection();
    }
}
