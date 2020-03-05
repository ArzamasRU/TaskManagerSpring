package ru.lavrov.tm.command.project;

import ru.lavrov.tm.api.ProjectService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectTasksListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin, Role.User);
    private static final String COMMAND = "project-tasks";
    private static final String DESCRIPTION = "Tasks of project.";

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
        final Scanner input = new Scanner(System.in);
        System.out.println("[Tasks of project]");
        System.out.println("enter project name:");
        final String projectName = input.nextLine();
        final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        final ProjectService projectService = bootstrap.getProjectService();
        final Collection<Task> taskList = projectService.getProjectTasks(projectName, currentUser.getId());
        if (taskList == null)
            return;
        for (final Task task: taskList) {
            if (task == null)
                continue;
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
