package ru.lavrov.tm.command.userCommand;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Collection;

public final class UserProjectListCommand extends AbstractCommand {
    private final boolean isSafe = false;
    private final Collection<Role> roles = null;

    public UserProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserProjectListCommand() {
        super();
    }

    @Override
    public String command() {
        return "user-project-list";
    }

    @Override
    public String description() {
        return "Show projects of user";
    }

    @Override
    public void execute() {
        System.out.println("[Show projects of user]");
        ProjectService projectService = bootstrap.getProjectService();
        User sessionUser = bootstrap.getSessionUser();
        int index = 0;
        for (Project project: projectService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + project);
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
