package ru.lavrov.tm.command.task;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin, Role.User);
    private static final String COMMAND = "task-clear";
    private static final String DESCRIPTION = "Remove all tasks.";

    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskCreateCommand() {
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
        System.out.println("[Task create]");
        System.out.println("enter name:");
        String taskName = input.nextLine();
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        TaskService taskService = bootstrap.getTaskService();
        taskService.createByName(taskName, projectName, currentUser.getId());
        System.out.println("[ok]");
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
