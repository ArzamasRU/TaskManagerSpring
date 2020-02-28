package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.exception.utilException.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.UserService;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

public final class UserRegisterCommand extends AbstractCommand {
    private final boolean isSafe = true;
    private final Collection<Role> roles = null;

    public UserRegisterCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserRegisterCommand() {
        super();
    }

    @Override
    public String command() {
        return "register";
    }

    @Override
    public String description() {
        return "registration new user";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Registration]");
        System.out.println("enter login:");
        String login = input.nextLine();
        System.out.println("enter password:");
        String password = input.nextLine();
        System.out.println("enter your role ('admin', 'user'):");
        String role = input.nextLine();
        try {
            password = HashUtil.getHash(password);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilAlgorithmNotExistsException();
        }
        UserService userService = bootstrap.getUserService();
        userService.persist(login, password, role);
        System.out.println("[User successfully registered]");
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