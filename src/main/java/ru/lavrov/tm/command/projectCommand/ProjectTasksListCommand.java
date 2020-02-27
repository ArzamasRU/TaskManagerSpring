package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public final class ProjectTasksListCommand extends AbstractCommand {
    public ProjectTasksListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectTasksListCommand() {
        super();
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
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[tasks of project]");
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        User sessionUser = bootstrap.getSessionUser();
        ProjectService projectService = bootstrap.getProjectService();
        for (Task task: projectService.getProjectTasksByUser(projectName, sessionUser)) {
            System.out.println(task);
        }
    }
}
