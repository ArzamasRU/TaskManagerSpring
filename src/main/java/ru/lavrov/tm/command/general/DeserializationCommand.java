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
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.constant.Constant.*;
import static ru.lavrov.tm.util.SerializationUtil.*;

public final class DeserializationCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN);
    @NotNull
    private static final String COMMAND = "deserialize";
    @NotNull
    private static final String DESCRIPTION = "Deserialize data.";

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
        System.out.println("[Deserialize data]");
        @Nullable final Collection<Project> projectList = read(PROJECTS_FILE_PATH);
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project == null)
                    throw new ProjectNotExistsException();
                projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList = read(TASKS_FILE_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task == null)
                    throw new TaskNotExistsException();
                taskService.persist(task);
            }
        @Nullable final Collection<User> userList = read(USERS_FILE_PATH);
        @Nullable final IUserService userService = bootstrap.getUserService();
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user == null)
                    throw new UserNotExistsException();
                userService.persist(user);
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
