package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.service.ProjectService;

public final class ProjectClearCommand extends AbstractCommand {
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
}
