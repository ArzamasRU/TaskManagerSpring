package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.util.InputUtil;

import java.util.Collection;

@NoArgsConstructor
public final class UserPasswordUpdateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "user-password-change";
    @NotNull
    private static final String DESCRIPTION = "Change user password.";

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
        System.out.println("[Change user password]");
        System.out.println("enter new password:");
        @Nullable final String newPassword = InputUtil.INPUT.nextLine();
        @Nullable final IUserService userService = bootstrap.getUserService();
        @Nullable final User sessionUser = bootstrap.getCurrentUser();
        userService.updatePassword(sessionUser.getId(), newPassword);
        System.out.println("[ok]");
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