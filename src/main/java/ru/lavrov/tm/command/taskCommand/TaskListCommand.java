package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;

public final class TaskListCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskListCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-list";
    }

    @Override
    public String description() {
        return "Show all tasks";
    }

    @Override
    public void execute() {
        System.out.println("[TASK LIST]");
        TaskService taskService = bootstrap.getTaskService();
        int index = 0;
        for (Task task: taskService.findAll()) {
            System.out.println(++index + ". " + task);
        }
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
