package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;

public final class ProjectClearCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectClearCommand() {
        super();
    }

    @Override
    public String command() {
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects.";
    }

    @Override
    public void execute() {
        ProjectService projectService = bootstrap.getProjectService();
        projectService.removeAll();
        System.out.println("[All projects removed]");
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
