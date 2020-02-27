package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.ProjectService;

public final class ProjectListCommand extends AbstractCommand {
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
        return "Show all projects.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        ProjectService projectService = bootstrap.getProjectService();
        User sessionUser = bootstrap.getSessionUser();
        int index = 0;
        for (Project project: projectService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + project);
        }
        System.out.println();
    }
}
