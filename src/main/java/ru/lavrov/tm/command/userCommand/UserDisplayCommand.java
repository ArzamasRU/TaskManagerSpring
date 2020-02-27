package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;

import java.util.Scanner;

public final class UserDisplayCommand extends AbstractCommand {
    public UserDisplayCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserDisplayCommand() {
        super();
    }

    @Override
    public String command() {
        return "user-display";
    }

    @Override
    public String description() {
        return "display user profile";
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        ProjectService projectService = bootstrap.getProjectService();
        TaskService taskService = bootstrap.getTaskService();
        User sessionUser = bootstrap.getSessionUser();

        System.out.println("[Display user profile]");
        System.out.println("user data:");
        System.out.println(sessionUser);
        System.out.println("attached projects:");
        int index = 0;
        for (Project project: projectService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + project);
        }
        System.out.println("attached tasks:");
        index = 0;
        for (Task task: taskService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + task);
        }
        System.out.println();
    }
}