package ru.lavrov.tm.command.user;

import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;

import java.util.Collection;

public final class UserProjectListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = null;
    private static final String COMMAND = "user-project-list";
    private static final String DESCRIPTION = "Show projects of user.";

    public UserProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserProjectListCommand() {
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
        System.out.println("[Show projects of user]");
        ProjectService projectService = bootstrap.getProjectService();
        User sessionUser = bootstrap.getCurrentUser();
        int index = 0;
        for (Project project: projectService.findAllByUser(sessionUser)) {
            System.out.println(++index + ". " + project);
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
