package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectAttachToUserCommand extends AbstractCommand {
    public ProjectAttachToUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectAttachToUserCommand() {
        super();
    }

    @Override
    public String command() {
        return "project-attach-to-user";
    }

    @Override
    public String description() {
        return "attach project to user";
    }

    @Override
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Attach project to user]");
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        User sessionUser = bootstrap.getSessionUser();
        ProjectService projectService = bootstrap.getProjectService();
        projectService.attachProjectToUser(projectName, sessionUser);
        System.out.println();
    }
}
