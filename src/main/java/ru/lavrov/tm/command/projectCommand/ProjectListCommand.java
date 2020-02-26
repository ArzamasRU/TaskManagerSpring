package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.service.ProjectService;

public final class ProjectListCommand extends AbstractCommand {
    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show all projects.";
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
}
