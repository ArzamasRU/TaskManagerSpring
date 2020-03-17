package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Task;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public final class UserDisplayCommand extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "user-display";
    @NotNull
    private static final String DESCRIPTION = "Display user profile.";

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
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        System.out.println("[Display user profile]");
        System.out.println("user data:");
        System.out.println(currentUser.getLogin());
        System.out.println("attached entities:");
        int index = 1;
        @Nullable final Collection<Project> projectList = projectService.findAll(currentUser.getId());
        if (projectList == null)
            return;
        for (@Nullable final IEntity project : projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
        }
        System.out.println("attached tasks:");
        @Nullable final Collection<Task> taskList = taskService.findAll(currentUser.getId());
        if (taskList == null)
            return;
        index = 1;
        for (@Nullable final IEntity task : taskList) {
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