package ru.lavrov.tm.command.helpCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public final class HelpCommand extends AbstractCommand {
    private final boolean isSafe = true;
    private final Collection<Role> roles = null;

    public HelpCommand() {
    }

    public HelpCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Show all commands";
    }

    @Override
    public void execute() {
        for (AbstractCommand command: bootstrap.getCommands()) {
            System.out.println(command.command() + ": " + command.description());
        }
        System.out.println();
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
