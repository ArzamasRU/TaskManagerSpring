package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.UserService;

import java.util.Scanner;

public final class UserUpdateCommand extends AbstractCommand {
    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserUpdateCommand() {
        super();
    }

    @Override
    public String command() {
        return "user-update";
    }

    @Override
    public String description() {
        return "update user profile";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Update user profile]");
        System.out.println("enter new login:");
        String newLogin = input.nextLine();
        UserService userService = bootstrap.getUserService();
        User sessionUser = bootstrap.getSessionUser();
        userService.changeLogin(sessionUser, newLogin);
        System.out.println();
    }
}