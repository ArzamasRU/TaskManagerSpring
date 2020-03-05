package ru.lavrov.tm.command;

import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public abstract class AbstractCommand {
    protected IServiceLocator bootstrap;

    public AbstractCommand(){
    }

    public IServiceLocator getBootstrap() {
        return bootstrap;
    }

    public abstract String getCommand();

    public abstract String getDescription();

    public abstract void execute() throws RuntimeException;

    public void setBootstrap(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract boolean isSafe();

    public abstract Collection<Role> getRoles();
}

