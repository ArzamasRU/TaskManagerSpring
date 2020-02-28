package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ProjectAttachToUserCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

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
        System.out.println("enter user login:");
        String userLogin = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
//        User sessionUser = bootstrap.getSessionUser();
//        projectService.attachProjectToUser(projectName, sessionUser);
        projectService.attachProjectToUser(projectName, userLogin);
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}
