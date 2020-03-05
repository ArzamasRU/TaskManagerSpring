package ru.lavrov.tm.command.project;

import ru.lavrov.tm.api.ProjectService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.role.Role;

import java.util.Arrays;
import java.util.Collection;

public final class ProjectListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    private static final Collection<Role> ROLES = Arrays.asList(Role.Admin);
    private static final String COMMAND = "project-list";
    private static final String DESCRIPTION = "Show all projects.";

    public ProjectListCommand() {
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
        System.out.println("[PROJECT LIST]");
        final ProjectService projectService = bootstrap.getProjectService();
        final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        int index = 1;
        final Collection<Project> projectList = projectService.findAllByUser(currentUser.getId());
        if (projectList == null)
            return;
        for (final Project project: projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
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
