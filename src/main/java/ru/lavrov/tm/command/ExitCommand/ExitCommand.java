package ru.lavrov.tm.command.ExitCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public final class ExitCommand extends AbstractCommand {
    private final boolean isSafe = true;
    private final Collection<Role> roles = null;

    public ExitCommand() {
        super();
    }

    public ExitCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "exit";
    }

    @Override
    public String description() {
        return "Exit.";
    }

    @Override
    public void execute() {
        System.out.println("[You left this wonderful program]");
    }

    @Override
    public boolean isSafe() {
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}
