package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.lavrov.tm.command.AbstractCommand;

@NoArgsConstructor
public final class UserLogoutCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @NotNull
    private static final String COMMAND = "logout";
    @NotNull
    private static final String DESCRIPTION = "End of session.";

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
        bootstrap.setCurrentToken(null);
        System.out.println("[ok]");
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}