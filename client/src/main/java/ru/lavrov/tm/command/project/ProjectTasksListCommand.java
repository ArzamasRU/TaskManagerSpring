package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.endpoint.Role;
import ru.lavrov.tm.endpoint.Session;
import ru.lavrov.tm.endpoint.Task;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.util.InputUtil;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public final class ProjectTasksListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "project-tasks";
    @NotNull
    private static final String DESCRIPTION = "Tasks of project.";

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
        System.out.println("[Tasks of project]");
        System.out.println("enter project name:");
        @Nullable final String projectName = InputUtil.INPUT.nextLine();
        @Nullable final Session currentSession = bootstrap.getCurrentSession();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        @Nullable final Collection<Task> taskList =
                projectEndpointService.getProjectEndpointPort().getProjectTasks(currentSession, projectName);
        if (taskList == null)
            return;
        for (@Nullable final Task task : taskList) {
            if (task == null)
                continue;
            System.out.println(task);
        }
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
