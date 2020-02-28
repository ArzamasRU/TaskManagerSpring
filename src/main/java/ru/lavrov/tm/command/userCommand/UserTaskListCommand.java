package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Collection;

public final class UserTaskListCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = null;

    public UserTaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserTaskListCommand() {
        super();
    }

    @Override
    public String command() {
        return "user-task-list";
    }

    @Override
    public String description() {
        return "Show tasks of user";
    }

    @Override
    public void execute() {
        System.out.println("[Show tasks of user]");
        TaskService taskService = bootstrap.getTaskService();
        User sessionUser = bootstrap.getSessionUser();
        int index = 0;
        for (Task task: taskService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + task);
        }
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
