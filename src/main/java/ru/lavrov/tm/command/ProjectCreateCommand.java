package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public final class ProjectCreateCommand extends AbstractCommand{
    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "project-create";
    }

    @Override
    public String description() {
        return "Create new project.";
    }

    @Override
    public void execute() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("[Project create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        projectService.createProject(command);
        System.out.println("[ok]");
        System.out.println();
    }
}
