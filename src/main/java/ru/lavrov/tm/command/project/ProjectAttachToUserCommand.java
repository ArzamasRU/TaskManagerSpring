package ru.lavrov.tm.command.project;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ProjectAttachToUserCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "project-attach-to-user";
    private static final String DESCRIPTION = "Attach project to user.";

    public ProjectAttachToUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectAttachToUserCommand() {
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
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Attach project to user]");
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        System.out.println("enter user login:");
        String userLogin = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        projectService.attachProjectToUser(projectName, currentUser.getId());
//        projectService.attachProjectToUser(projectName, userLogin);
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
