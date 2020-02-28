package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectCreateCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectCreateCommand() {
        super();
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
    public void execute() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Project create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        ProjectService projectService = bootstrap.getProjectService();
        projectService.persist(command);
        System.out.println("[ok]");
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
