package ru.lavrov.tm.command;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectRenameCommand extends AbstractCommand{

    public ProjectRenameCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "project-rename";
    }

    @Override
    public String description() {
        return "Rename project";
    }

    @Override
    public void execute() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("[project remove]");
        System.out.println("enter name:");
        String oldName = input.nextLine();
        System.out.println("enter new name:");
        String newName = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        projectService.renameProject(oldName, newName);
        System.out.println("[Project renamed]");
        System.out.println();
    }
}
