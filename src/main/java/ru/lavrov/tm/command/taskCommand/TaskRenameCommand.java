package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class TaskRenameCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = Arrays.asList(Role.Admin);

    public TaskRenameCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskRenameCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-rename";
    }

    @Override
    public String description() {
        return "Rename task";
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
        return isSafe;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles;
    }
}
