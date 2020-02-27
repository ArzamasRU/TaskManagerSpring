package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.TaskService;

import java.util.Scanner;

public class TaskAttachToUserCommand extends AbstractCommand {
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
        User sessionUser = bootstrap.getSessionUser();
        TaskService taskService = bootstrap.getTaskService();
        taskService.attachTaskToUser(taskName, sessionUser);
        System.out.println();
    }
}
