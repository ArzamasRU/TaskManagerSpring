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
import static ru.lavrov.tm.util.JAXBUtil.readFromXMLByJAXB;

public final class DataFromXMLByJAXBСommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "from-XML-by-JAXB";
    @NotNull
    private static final String DESCRIPTION = "Externalize data from XML by JAXB.";

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
        System.out.println("[Externalization data from XML By JAXB]");
        @Nullable final Collection<Project> projectList = readFromXMLByJAXB(Project.class, EXTERNALIZATION_DIR_PATH);
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project == null)
                    throw new ProjectNotExistsException();
                projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList = readFromXMLByJAXB(Task.class, EXTERNALIZATION_DIR_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task == null)
                    throw new TaskNotExistsException();
                taskService.persist(task);
            }
        @Nullable final Collection<User> userList = readFromXMLByJAXB(User.class, EXTERNALIZATION_DIR_PATH);
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
