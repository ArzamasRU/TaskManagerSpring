package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;

public final class UserLogoutCommand extends AbstractCommand {
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
}