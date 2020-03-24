package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.endpoint.Role;
import ru.lavrov.tm.endpoint.Session;
import ru.lavrov.tm.util.InputUtil;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "project-create";
    @NotNull
    private static final String DESCRIPTION = "Create new project.";

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
        System.out.println("[Project create]");
        @Nullable final Session currentSession = bootstrap.getCurrentSession();
        System.out.println("enter name:");
        @NotNull final String projectName = InputUtil.INPUT.nextLine();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        if (projectEndpointService.getProjectEndpointPort().createByProjectName(currentSession, projectName))
            System.out.println("[ok]");
        else
            System.out.println("[error]");
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
