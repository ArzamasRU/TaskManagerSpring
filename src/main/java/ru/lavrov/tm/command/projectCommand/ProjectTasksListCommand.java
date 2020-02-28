package ru.lavrov.tm.command.projectCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectTasksListCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public ProjectTasksListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectTasksListCommand() {
        super();
    }

    @Override
    public String command() {
        return "project-tasks";
    }

    @Override
    public String description() {
        return "Tasks of project";
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
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}
