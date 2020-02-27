package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.service.TaskService;

public final class TaskClearCommand extends AbstractCommand {
    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskClearCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-clear";
    }

    @Override
    public String description() {
        return "Remove all tasks.";
    }

    @Override
    public void execute() {
        TaskService taskService = bootstrap.getTaskService();
        taskService.removeAll();
        System.out.println("[All projects removed]");
        System.out.println();
    }
}
