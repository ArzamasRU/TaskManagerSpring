package ru.lavrov.tm.command;

import ru.lavrov.tm.api.ServiceLocator;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public abstract class AbstractCommand {
    protected ServiceLocator bootstrap;

    public AbstractCommand(){
    }

    public ServiceLocator getBootstrap() {
        return bootstrap;
    }

    public abstract String getCommand();

    public abstract String getDescription();

    public abstract void execute() throws RuntimeException;

    public void setBootstrap(final ServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract boolean isSafe();

    public abstract Collection<Role> getRoles();
}

