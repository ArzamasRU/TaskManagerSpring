package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public final class ProjectRemoveCommand extends AbstractCommand {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove selected project.";
    }

    @Override
    public void execute() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("[project remove]");
        System.out.println("enter name:");
        String command = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        projectService.removeProject(command);
        System.out.println("[Project removed]");
        System.out.println();
    }
}
