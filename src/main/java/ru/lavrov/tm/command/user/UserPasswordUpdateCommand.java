package ru.lavrov.tm.command.user;

import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;

import java.util.Collection;
import java.util.Scanner;

public final class UserPasswordUpdateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "user-password-change";
    private static final String DESCRIPTION = "Change user password.";

    public UserPasswordUpdateCommand() {
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
        final Scanner input = new Scanner(System.in);
        System.out.println("[Change user password]");
        System.out.println("enter new password:");
        final String newPassword = input.nextLine();
        final IUserService userService = bootstrap.getUserService();
        final User sessionUser = bootstrap.getCurrentUser();
        userService.updatePassword(sessionUser.getId(), newPassword);
        System.out.println("[ok]");
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