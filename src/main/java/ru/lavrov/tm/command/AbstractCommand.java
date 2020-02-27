package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;

public abstract class AbstractCommand {
    protected Bootstrap bootstrap;
    protected boolean isSafe;

    public AbstractCommand(){
    }

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract String command();

    public abstract String description();

    public abstract void execute() throws RuntimeException;

    public void setBootstrap(Bootstrap bootstrap) {
        bootstrap = bootstrap;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }
}

