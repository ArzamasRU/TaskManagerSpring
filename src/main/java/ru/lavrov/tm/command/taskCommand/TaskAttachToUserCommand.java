package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class TaskAttachToUserCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public TaskAttachToUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskAttachToUserCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-attach-to-user";
    }

    @Override
    public String description() {
        return "attach task to user";
    }

    @Override
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Attach task to user]");
        System.out.println("enter task name:");
        String taskName = input.nextLine();
        System.out.println("enter login:");
        String login = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        //        User sessionUser = bootstrap.getSessionUser();
//        taskService.attachTaskToUser(sessionUser, login);
        taskService.attachTaskToUser(taskName, login);
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
