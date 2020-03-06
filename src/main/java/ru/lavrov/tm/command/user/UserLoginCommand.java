package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.exception.util.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

@NoArgsConstructor
public final class UserLoginCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "login";
    @NotNull
    private static final String DESCRIPTION = "Authorization.";

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
        final Scanner input = new Scanner(System.in);
        System.out.println("[Please Log in]");
        System.out.println("enter login:");
        final String login = input.nextLine();
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
        return SAFE;
    }

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}