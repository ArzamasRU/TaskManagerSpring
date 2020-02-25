package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;

public abstract class AbstractCommand {
    protected Bootstrap bootstrap;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        bootstrap = bootstrap;
    }
    public abstract String command();
    public abstract String description();
    public abstract void execute() throws Exception;
}

