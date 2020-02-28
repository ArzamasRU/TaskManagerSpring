package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.exception.utilException.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

public final class UserLoginCommand extends AbstractCommand {
    private final boolean isSafe = true;
    private final Collection<Role> roles = null;

    public UserLoginCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserLoginCommand() {
        super();
    }

    @Override
    public String command() {
        return "login";
    }

    @Override
    public String description() {
        return "authorization";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Please Log in]");
        System.out.println("enter login:");
        String login = input.nextLine();
        System.out.println("enter password:");
        String password = input.nextLine();
        try {
            password = HashUtil.getHash(password);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilAlgorithmNotExistsException();
        }
        bootstrap.login(login, password);
        System.out.println("[You are logged in]");
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