package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.util.InputUtil;

@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
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
        @Nullable final String token = bootstrap.getCurrentToken();
        System.out.println("enter name:");
        @NotNull final String projectName = InputUtil.INPUT.nextLine();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        System.out.println("ProjectCreateCommand");
        System.out.println(token);
        projectEndpointService.getProjectEndpointPort().createByProjectName(token, projectName);
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
