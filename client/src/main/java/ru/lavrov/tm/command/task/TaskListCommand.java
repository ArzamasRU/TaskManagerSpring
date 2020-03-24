package ru.lavrov.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.command.AbstractCommand;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN);
    @NotNull
    private static final String COMMAND = "task-list";
    @NotNull
    private static final String DESCRIPTION = "Show all tasks.";

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
        @Nullable final Session currentSession = bootstrap.getCurrentSession();
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        @Nullable final Collection<Task> taskList =
                taskEndpointService.getTaskEndpointPort().findAllTasks(currentSession);
        if (taskList == null)
            return;
        int index = 1;
        for (@Nullable final Task task : taskList) {
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

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
