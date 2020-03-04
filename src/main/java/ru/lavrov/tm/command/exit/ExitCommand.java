package ru.lavrov.tm.command.Exit;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public final class ExitCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "exit";
    private static final String DESCRIPTION = "Exit.";

    public ExitCommand() {
        super();
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
        System.out.println("[You left this wonderful program]");
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
