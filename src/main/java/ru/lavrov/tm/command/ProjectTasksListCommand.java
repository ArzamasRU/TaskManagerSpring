package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public final class ProjectTasksListCommand extends AbstractCommand{
    public ProjectTasksListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "exit";
    }

    @Override
    public String description() {
        return "Exit.";
    }

    @Override
    public void execute() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("[tasks of project]");
        System.out.println("enter project name:");
        String command = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        for (Task task: projectService.displayProjectTasks(command)) {
            System.out.println(task);
        }
    }
}
