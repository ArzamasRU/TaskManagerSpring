package ru.lavrov.tm.command.task;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class TaskDetachFromProjectCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "task-detach-from-project";
    private static final String DESCRIPTION = "Detach task from project.";

    public TaskDetachFromProjectCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskDetachFromProjectCommand() {
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
        return SAFE;
    }

    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}