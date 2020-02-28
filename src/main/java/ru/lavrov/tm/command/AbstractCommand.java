package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public abstract class AbstractCommand {
    protected Bootstrap bootstrap;
    protected boolean isSafe;
    protected Collection<Role> roles;

    public AbstractCommand(){
    }

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public abstract String command();

    public abstract String description();

    public abstract void execute() throws RuntimeException;

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract boolean isSafe();

    public abstract Collection<Role> getRoles();
}

