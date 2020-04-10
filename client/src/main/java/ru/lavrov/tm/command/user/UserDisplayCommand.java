package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.*;

import java.util.Collection;

@NoArgsConstructor
public final class UserDisplayCommand extends AbstractCommand {
    private static final boolean SAFE = false;

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
        @NotNull final String token = bootstrap.getCurrentToken();
        @Nullable final UserDTO user = userEndpointService.getUserEndpointPort().getUser(token);
        @NotNull final Collection<ProjectDTO> projectList =
                userEndpointService.getUserEndpointPort().getUserProjects(token);
        @NotNull final Collection<TaskDTO> taskList =
                userEndpointService.getUserEndpointPort().getUserTasks(token);
        System.out.println("[Display user profile]");
        System.out.println("user data:");
        System.out.println(user.getLogin());
        System.out.println("attached projects:");
        int index = 1;
        for (@Nullable final ProjectDTO project : projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
        }
        System.out.println("attached tasks:");
        index = 1;
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