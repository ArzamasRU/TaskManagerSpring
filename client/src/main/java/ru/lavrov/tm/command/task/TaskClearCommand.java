package ru.lavrov.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.Role;
import ru.lavrov.tm.endpoint.Session;
import ru.lavrov.tm.endpoint.TaskEndpointService;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public final class TaskClearCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN);
    @NotNull
    private static final String COMMAND = "task-clear";
    @NotNull
    private static final String DESCRIPTION = "Remove all tasks.";

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
        @Nullable final Session currentSession = bootstrap.getCurrentSession();
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        if (taskEndpointService.getTaskEndpointPort().removeAll(currentSession))
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
