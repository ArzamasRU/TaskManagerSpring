package ru.lavrov.tm.command.user;

import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.exception.util.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

public final class UserRegisterCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "register";
    private static final String DESCRIPTION = "Registration of new user.";

    public UserRegisterCommand() {
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
        System.out.println("[Registration]");
        System.out.println("enter login:");
        final String login = input.nextLine();
        System.out.println("enter password:");
        String password = input.nextLine();
        System.out.println("enter your role ('admin', 'user'):");
        final String role = input.nextLine();
        try {
            password = HashUtil.getHash(password);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilAlgorithmNotExistsException();
        }
        final IUserService userService = bootstrap.getUserService();
        userService.createByLogin(login, password, role);
        System.out.println("[User successfully registered]");
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