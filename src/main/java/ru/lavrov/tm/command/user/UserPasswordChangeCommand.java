package ru.lavrov.tm.command.user;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.UserService;

import java.util.Collection;
import java.util.Scanner;

public final class UserPasswordChangeCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "user-password-change";
    private static final String DESCRIPTION = "Change user password.";

    public UserPasswordChangeCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserPasswordChangeCommand() {
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
        Scanner input = new Scanner(System.in);
        System.out.println("[Change user password]");
        System.out.println("enter new password:");
        String newPassword = input.nextLine();
        UserService userService = bootstrap.getUserService();
        User sessionUser = bootstrap.getCurrentUser();
        userService.changePassword(sessionUser, newPassword);
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