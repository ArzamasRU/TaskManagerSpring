package ru.lavrov.tm.command.user;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.TaskService;

import java.util.Collection;

public final class UserTaskListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "user-task-list";
    private static final String DESCRIPTION = "Show tasks of user.";

    public UserTaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserTaskListCommand() {
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
        System.out.println("[Show tasks of user]");
        TaskService taskService = bootstrap.getTaskService();
        User sessionUser = bootstrap.getCurrentUser();
        int index = 0;
        for (Task task: taskService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + task);
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
