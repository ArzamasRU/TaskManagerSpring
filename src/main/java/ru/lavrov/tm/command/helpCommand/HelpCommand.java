package ru.lavrov.tm.command.helpCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;

public final class HelpCommand extends AbstractCommand {
    public HelpCommand() {
    }

    public HelpCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Show all commands";
    }

    @Override
    public void execute() {
        for (AbstractCommand command: bootstrap.getCommands()) {
            System.out.println(command.command() + ": " + command.description());
        }
        System.out.println();
    }

}
