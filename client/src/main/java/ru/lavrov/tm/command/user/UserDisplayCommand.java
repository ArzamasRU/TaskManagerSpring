package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.*;

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
        @NotNull final UserEndpointService userEndpointService = bootstrap.getUserEndpointService();
        @NotNull final Session currentSession = bootstrap.getCurrentSession();
        @Nullable final User user = userEndpointService.getUserEndpointPort().getUser(currentSession);
        @NotNull final Collection<Project> projectList =
                userEndpointService.getUserEndpointPort().getUserProjects(currentSession);
        @NotNull final Collection<Task> taskList =
                userEndpointService.getUserEndpointPort().getUserTasks(currentSession);
        System.out.println("[Display user profile]");
        System.out.println("user data:");
        System.out.println(user.getLogin());
        System.out.println("attached projects:");
        int index = 1;
        for (@Nullable final Project project : projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
        }
        System.out.println("attached tasks:");
        index = 1;
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