package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.service.TaskService;

import java.util.Scanner;

public final class TaskAttachToProjectCommand extends AbstractCommand {
    public TaskAttachToProjectCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskAttachToProjectCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-attach-to-project";
    }

    @Override
    public String description() {
        return "Attach task to project.";
    }

    @Override
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Attach task to project]");
        System.out.println("enter task name:");
        String taskName = input.nextLine();
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        taskService.attachTaskToProject(taskName, projectName);
        System.out.println();
    }
}
