package ru.lavrov.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.enumerate.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
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
        @Nullable final Scanner input = new Scanner(System.in);
        System.out.println("[Task create]");
        System.out.println("enter name:");
        @Nullable final String taskName = input.nextLine();
        System.out.println("enter project name:");
        @Nullable final String projectName = input.nextLine();
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        taskService.createByName(taskName, projectName, currentUser.getId());
        System.out.println("[ok]");
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
