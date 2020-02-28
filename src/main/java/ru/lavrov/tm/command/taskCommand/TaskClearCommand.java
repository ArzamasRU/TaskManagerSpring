package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;

public final class TaskClearCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

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

    @Override
    public boolean isSafe() {
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}
