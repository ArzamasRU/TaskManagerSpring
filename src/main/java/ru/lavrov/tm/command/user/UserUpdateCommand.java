package ru.lavrov.tm.command.user;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.UserService;

import java.util.Collection;
import java.util.Scanner;

public final class UserUpdateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "user-update";
    private static final String DESCRIPTION = "Update user profile.";

    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserUpdateCommand() {
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
        System.out.println("[Update user profile]");
        System.out.println("enter new login:");
        String newLogin = input.nextLine();
        User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        UserService userService = bootstrap.getUserService();
        userService.updateLogin(currentUser.getId(), newLogin);
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