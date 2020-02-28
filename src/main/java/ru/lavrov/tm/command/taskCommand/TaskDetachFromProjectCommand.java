package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class TaskDetachFromProjectCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public TaskDetachFromProjectCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskDetachFromProjectCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-detach-from-project";
    }

    @Override
    public String description() {
        return "detach task from project";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Detach task from project]");
        System.out.println("enter task:");
        String taskName = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        taskService.detachTaskfromProject(taskName);
        //        taskService.detachTaskfromUser(taskName);
        System.out.println("[ok]");
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