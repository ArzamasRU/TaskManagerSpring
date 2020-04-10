package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.ProjectEndpointService;

@NoArgsConstructor
public final class ProjectClearCommand extends AbstractCommand {
    private static final boolean SAFE = false;

    @NotNull
    private static final String COMMAND = "project-clear";
    @NotNull
    private static final String DESCRIPTION = "Remove all entities.";

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
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        projectEndpointService.getProjectEndpointPort().removeAll(token);
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
