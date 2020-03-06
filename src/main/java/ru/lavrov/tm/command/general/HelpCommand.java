package ru.lavrov.tm.command.general;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

@NoArgsConstructor
public final class HelpCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "help";
    @NotNull
    private static final String DESCRIPTION = "Show all commands.";

    @NotNull
    @Override
    public String getCommand() {
        return COMMAND;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        final Collection<AbstractCommand> list = bootstrap.getCommands();
        if (list == null)
            return;
        for (@Nullable final AbstractCommand command: list) {
            if (command == null)
                continue;
            System.out.println(command.getCommand() + ": " + command.getDescription());
        }
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
