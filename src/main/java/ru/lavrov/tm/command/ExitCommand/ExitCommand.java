package ru.lavrov.tm.command.ExitCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;

public final class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super();
    }

    public ExitCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "exit";
    }

    @Override
    public String description() {
        return "Exit.";
    }

    @Override
    public void execute() {
        System.out.println("[You left this wonderful program]");
    }
}
