package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.service.TaskService;

import java.util.Scanner;

public final class TaskAttachCommand extends AbstractCommand {
    public TaskAttachCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "task-attach";
    }

    @Override
    public String description() {
        return "Attach task to project.";
    }

    @Override
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[task attach]");
        System.out.println("enter task name:");
        String taskName = input.nextLine();
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        taskService.attachTask(taskName, projectName);
        System.out.println("[ok]");
        System.out.println();
    }
}
