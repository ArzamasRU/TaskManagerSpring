package ru.lavrov.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.ProjectEndpointService;
import ru.lavrov.tm.endpoint.Task;
import ru.lavrov.tm.endpoint.TaskDTO;
import ru.lavrov.tm.util.InputUtil;

import java.util.Collection;

@NoArgsConstructor
public final class ProjectTasksListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
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
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        @Nullable final Collection<TaskDTO> taskList =
                projectEndpointService.getProjectEndpointPort().getProjectTasks(token, projectName);
        if (taskList == null)
            return;
        for (@Nullable final TaskDTO task : taskList) {
            if (task == null)
                continue;
            System.out.println(task);
        }
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
