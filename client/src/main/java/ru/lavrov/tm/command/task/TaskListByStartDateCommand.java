package ru.lavrov.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.Task;
import ru.lavrov.tm.endpoint.TaskDTO;
import ru.lavrov.tm.endpoint.TaskEndpointService;

import java.util.Collection;

@NoArgsConstructor
public final class TaskListByStartDateCommand extends AbstractCommand {
    private static final boolean SAFE = false;

    @NotNull
    private static final String COMMAND = "task-list-by-start-date";
    @NotNull
    private static final String DESCRIPTION = "Show all tasks sorted by start date.";

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
        System.out.println("[TASK LIST]");
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        @Nullable final Collection<TaskDTO> taskList =
                taskEndpointService.getTaskEndpointPort().findAllTasksByStartDate(token);
        if (taskList == null)
            return;
        int index = 1;
        for (@Nullable final TaskDTO task : taskList) {
            if (task == null)
                continue;
            System.out.println(index++ + ". " + task);
        }
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
