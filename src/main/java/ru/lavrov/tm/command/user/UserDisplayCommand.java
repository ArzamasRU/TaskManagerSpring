package ru.lavrov.tm.command.user;

import ru.lavrov.tm.api.ProjectService;
import ru.lavrov.tm.api.TaskService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class UserDisplayCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin, Role.User);
    private static final String COMMAND = "user-display";
    private static final String DESCRIPTION = "Display user profile.";

    public UserDisplayCommand() {
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
        final Scanner input = new Scanner(System.in);
        final ProjectService projectService = bootstrap.getProjectService();
        final TaskService taskService = bootstrap.getTaskService();
        final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        System.out.println("[Display user profile]");
        System.out.println("user data:");
        System.out.println(currentUser.getLogin());
        System.out.println("attached projects:");
        int index = 1;
        final Collection<Project> projectList = projectService.findAllByUser(currentUser.getId());
        if (projectList == null)
            return;
        for (final Project project: projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
        }
        System.out.println("attached tasks:");
        index = 1;
        final Collection<Task> taskList = taskService.findAllByUser(currentUser.getId());
        if (taskList == null)
            return;
        for (final Task task: taskList) {
            if (task == null)
                continue;
            System.out.println(index++ + ". " + task);
        }
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