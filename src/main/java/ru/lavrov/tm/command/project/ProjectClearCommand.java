package ru.lavrov.tm.command.project;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;

public final class ProjectClearCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "project-clear";
    private static final String DESCRIPTION = "Remove all projects.";

    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectClearCommand() {
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
        ProjectService projectService = bootstrap.getProjectService();
        projectService.removeAll();
        System.out.println("[All projects removed]");
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
