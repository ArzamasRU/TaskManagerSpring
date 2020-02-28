package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;

public final class ProjectListCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectListCommand() {
        super();
    }

    @Override
    public String command() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show all projects";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        ProjectService projectService = bootstrap.getProjectService();
        int index = 0;
        for (Project project: projectService.findAll()) {
            System.out.println(++index + ". " + project);
        }
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
