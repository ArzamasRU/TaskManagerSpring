package ru.lavrov.tm.command.task;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class TaskRenameCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "task-rename";
    private static final String DESCRIPTION = "Rename task.";

    public TaskRenameCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        Scanner input = new Scanner(System.in);
        System.out.println("[task remove]");
        System.out.println("enter name:");
        String oldName = input.nextLine();
        System.out.println("enter new name:");
        String newName = input.nextLine();
        TaskService taskService = bootstrap.getTaskService();
        taskService.renameTask(oldName, newName);
        System.out.println("[Task renamed]");
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
