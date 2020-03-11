package ru.lavrov.tm.command.general;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SerializeCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "serialize";
    @NotNull
    private static final String DESCRIPTION = "Serialize data.";

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
        System.out.println("[Serialize data]");
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final IUserService userService = bootstrap.getUserService();
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        @NotNull final FileOutputStream userOutput = new FileOutputStream("src/main/file/users");
        @NotNull final FileOutputStream projectOutput = new FileOutputStream("src/main/file/projects");
        @NotNull final FileOutputStream taskOutput = new FileOutputStream("src/main/file/tasks");
        @Nullable final Collection<Project> projectList = projectService.findAll(currentUser.getId());
        @Nullable final Collection<Task> taskList = taskService.findAll(currentUser.getId());
        @Nullable final Collection<User> userList = userService.findAll(currentUser.getId());
        try {
            final ObjectOutputStream projectOutputStream = new ObjectOutputStream(projectOutput);
            projectOutputStream.writeObject(projectList);
            final ObjectOutputStream taskOutputStream = new ObjectOutputStream(taskOutput);
            taskOutputStream.writeObject(taskList);
            final ObjectOutputStream userOutputStream = new ObjectOutputStream(userOutput);
            userOutputStream.writeObject(userList);
            System.out.println("[SERIALIZATION COMPLETED]");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

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
