package ru.lavrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.Task;
import ru.lavrov.tm.endpoint.TaskDTO;
import ru.lavrov.tm.endpoint.TaskEndpointService;
import ru.lavrov.tm.util.InputUtil;

import java.util.Collection;

public final class TaskFindByName extends AbstractCommand {
    private static final boolean SAFE = false;

    @NotNull
    private static final String COMMAND = "task-find-by-name";
    @NotNull
    private static final String DESCRIPTION = "Show task by name.";

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
        System.out.println("enter name:");
        @Nullable final String name = InputUtil.INPUT.nextLine();
        @Nullable final String token = bootstrap.getCurrentToken();
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        @Nullable final Collection<TaskDTO> taskList =
                taskEndpointService.getTaskEndpointPort().findAllTasksByDescPart(token, name);
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
