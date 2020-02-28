package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.UserService;

import java.util.Collection;
import java.util.Scanner;

public final class UserPasswordChangeCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = null;

    public UserPasswordChangeCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserPasswordChangeCommand() {
        super();
    }

    @Override
    public String command() {
        return "user-password-change";
    }

    @Override
    public String description() {
        return "change user password";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Change user password]");
        System.out.println("enter new password:");
        String newPassword = input.nextLine();
        UserService userService = bootstrap.getUserService();
        User sessionUser = bootstrap.getSessionUser();
        userService.changePassword(sessionUser, newPassword);
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