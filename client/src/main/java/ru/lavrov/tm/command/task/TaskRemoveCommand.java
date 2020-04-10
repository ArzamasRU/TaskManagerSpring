package ru.lavrov.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.TaskEndpointService;
import ru.lavrov.tm.util.InputUtil;

@NoArgsConstructor
public final class TaskRemoveCommand extends AbstractCommand {
    private static final boolean SAFE = false;

    @NotNull
    private static final String COMMAND = "task-remove";
    @NotNull
    private static final String DESCRIPTION = "Remove selected task.";

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
        System.out.println("[task remove]");
        System.out.println("enter name:");
        @Nullable final String taskName = InputUtil.INPUT.nextLine();
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        taskEndpointService.getTaskEndpointPort().removeTaskByName(token, taskName);
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}
