package ru.lavrov.tm.command.taskCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.TaskService;

public final class TaskListCommand extends AbstractCommand {
    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskListCommand() {
        super();
    }

    @Override
    public String command() {
        return "task-list";
    }

    @Override
    public String description() {
        return "Show all tasks.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK LIST]");
        TaskService taskService = bootstrap.getTaskService();
        User sessionUser = bootstrap.getSessionUser();
        int index = 0;
        for (Task task: taskService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + task);
        }
        System.out.println();
    }
}
