package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class TaskDetachFromUserCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public TaskDetachFromUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskDetachFromUserCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-detach-from-user";
    }

    @Override
    public String description() {
        return "detach task from user";
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
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}