package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.service.TaskService;

import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand{
    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create new task.";
    }

    @Override
    public void execute() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("[Task create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        taskService.createTask(command);
        System.out.println("[ok]");
        System.out.println();
    }
}