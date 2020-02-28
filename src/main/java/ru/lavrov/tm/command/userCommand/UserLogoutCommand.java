package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

public final class UserLogoutCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = null;

    public UserLogoutCommand(){
        super();
    }

    public UserLogoutCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "logout";
    }

    @Override
    public String description() {
        return "end of session";
    }

    @Override
    public void execute() {
        bootstrap.logout();
        System.out.println("you left the session");
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