package ru.lavrov.tm.command.task;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class TaskDetachFromUserCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "task-detach-from-user";
    private static final String DESCRIPTION = "detach task from user.";

    public TaskDetachFromUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskDetachFromUserCommand() {
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
        System.out.println("[Detach task from user]");
        System.out.println("enter task:");
        String taskName = input.nextLine();
        System.out.println("enter login:");
        String login = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
//        User sessionUser = bootstrap.getSessionUser();
//        taskService.detachTaskfromUser(sessionUser, taskName);
        taskService.detachTaskfromUser(login, taskName);
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