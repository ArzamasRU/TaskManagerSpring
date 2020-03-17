package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.util.InputUtil;

import java.util.Collection;

@NoArgsConstructor
public final class UserLoginCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "login";
    @NotNull
    private static final String DESCRIPTION = "Authorization.";

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
        System.out.println("[Please Log in]");
        System.out.println("enter login:");
        @Nullable final String login = InputUtil.INPUT.nextLine();
        System.out.println("enter password:");
        @Nullable String password = InputUtil.INPUT.nextLine();
        bootstrap.login(login, password);
        System.out.println("[You are logged in]");
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