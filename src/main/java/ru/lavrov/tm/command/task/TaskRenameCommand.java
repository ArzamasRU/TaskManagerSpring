package ru.lavrov.tm.command.task;

import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class TaskRenameCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin, Role.User);
    private static final String COMMAND = "task-rename";
    private static final String DESCRIPTION = "Rename task.";

    public TaskRenameCommand() {
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
        System.out.println("[task rename]");
        System.out.println("enter project name:");
        final String projectName = input.nextLine();
        System.out.println("enter task name:");
        final String oldName = input.nextLine();
        System.out.println("enter new task name:");
        final String newName = input.nextLine();
        final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        final ITaskService taskService = bootstrap.getTaskService();
        taskService.renameTask(projectName ,oldName, newName, currentUser.getId());
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
