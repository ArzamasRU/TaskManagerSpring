package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.util.InputUtil;

import java.util.Collection;

@NoArgsConstructor
public final class UserRegisterCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "register";
    @NotNull
    private static final String DESCRIPTION = "Registration of new user.";

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
        System.out.println("[Registration]");
        System.out.println("enter login:");
        @Nullable final String login = InputUtil.INPUT.nextLine();
        System.out.println("enter password:");
        @Nullable final String password = InputUtil.INPUT.nextLine();
        System.out.println("enter your role ('admin', 'user'):");
        @Nullable final String role = InputUtil.INPUT.nextLine();
        @Nullable final IUserService userService = bootstrap.getUserService();
        userService.createByLogin(login, password, role);
        System.out.println("[You successfully registered]");
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