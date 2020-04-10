package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.util.InputUtil;

@NoArgsConstructor
public final class ProjectRemoveCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @NotNull
    private static final String COMMAND = "project-remove";
    @NotNull
    private static final String DESCRIPTION = "Remove selected project.";

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
        System.out.println("[project remove]");
        System.out.println("enter name:");
        @Nullable final String projectName = InputUtil.INPUT.nextLine();
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        projectEndpointService.getProjectEndpointPort().removeProjectByName(token, projectName);
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
