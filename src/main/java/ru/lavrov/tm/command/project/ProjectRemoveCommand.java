package ru.lavrov.tm.command.project;

import ru.lavrov.tm.api.ProjectService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectRemoveCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin, Role.User);
    private static final String COMMAND = "project-remove";
    private static final String DESCRIPTION = "Remove selected project.";

    public ProjectRemoveCommand() {
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
        final Scanner input = new Scanner(System.in);
        System.out.println("[project remove]");
        System.out.println("enter name:");
        final String projectName = input.nextLine();
        final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        final ProjectService projectService = bootstrap.getProjectService();
        projectService.removeProjectByName(projectName, currentUser.getId());
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