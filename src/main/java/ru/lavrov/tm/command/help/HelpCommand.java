package ru.lavrov.tm.command.help;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public final class HelpCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "help";
    private static final String DESCRIPTION = "Show all commands.";

    public HelpCommand() {
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        for (AbstractCommand command: bootstrap.getCommands()) {
            System.out.println(command.getCommand() + ": " + command.getDescription());
        }
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }

    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
