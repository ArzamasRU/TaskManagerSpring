package ru.lavrov.tm.command.project;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectDetachFromUserCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "project-detach-from-user";
    private static final String DESCRIPTION = "Detach project from user.";

    public ProjectDetachFromUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectDetachFromUserCommand() {
        super();
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
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
        projectService.detachProjectFromUser(userLogin, projectName);
        System.out.println("[ok]");
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }

    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}