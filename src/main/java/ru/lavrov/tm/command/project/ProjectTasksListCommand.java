package ru.lavrov.tm.command.project;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectTasksListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "project-tasks";
    private static final String DESCRIPTION = "Tasks of project.";

    public ProjectTasksListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectTasksListCommand() {
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
        System.out.println("[Tasks of project]");
        System.out.println("enter project name:");
        String projectName = input.nextLine();
//        User sessionUser = bootstrap.getSessionUser();
//        for (Task task: projectService.getProjectTasksByUser(projectName, sessionUser)) {
//            System.out.println(task);
//        }
        ProjectService projectService = bootstrap.getProjectService();
        for (Task task: projectService.getProjectTasks(projectName)) {
            System.out.println(task);
        }
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
