package ru.lavrov.tm.service;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IService;
import ru.lavrov.tm.util.ConnectionUtil;

import java.sql.Connection;

public abstract class AbstractService implements IService {
//    @NotNull
//    protected final IRepository repository;

    @Nullable
    public Connection getConnection() {
        return ConnectionUtil.getConnection();
    }

//    public AbstractService(@NotNull final IRepository abstractRepository) {
//        this.repository = abstractRepository;
//    }
}
