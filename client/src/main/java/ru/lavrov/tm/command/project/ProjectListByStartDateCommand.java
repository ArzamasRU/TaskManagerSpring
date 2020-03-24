package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.Project;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.endpoint.Role;
import ru.lavrov.tm.endpoint.Session;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@NoArgsConstructor
public final class ProjectListByStartDateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "project-list-by-start-date";
    @NotNull
    private static final String DESCRIPTION = "Show all projects sorted by start date.";

    @NotNull
    @Override
    public String getCommand() {
        return COMMAND;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        @Nullable final Session currentSession = bootstrap.getCurrentSession();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        @Nullable final Collection<Project> projectList =
                projectEndpointService.getProjectEndpointPort().findAllByStartDate(currentSession);
        if (projectList == null)
            return;
        int index = 1;
        for (@Nullable final Project project : projectList) {
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

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
