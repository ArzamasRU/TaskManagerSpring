package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectDetachFromUserCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public ProjectDetachFromUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectDetachFromUserCommand() {
        super();
    }

    @Override
    public String command() {
        return "project-detach-from-user";
    }

    @Override
    public String description() {
        return "detach project from user";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("[Detach project from user]");
        System.out.println("enter project:");
        String projectName = input.nextLine();
        System.out.println("enter user login:");
        String userLogin = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
//        User sessionUser = bootstrap.getSessionUser();
//        projectService.detachProjectfromUser(sessionUser, projectName);
        projectService.detachProjectfromUser(userLogin, projectName);
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