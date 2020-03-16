package ru.lavrov.tm.command.general;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.ExternalizationStorage;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.lavrov.tm.constant.Constant.*;
import static ru.lavrov.tm.util.JAXBUtil.readFromJSONByJAXB;


public final class DataFromJSONByFasterXMLCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "from-JSON-by-fasterXML";
    @NotNull
    private static final String DESCRIPTION = "Externalize data from JSON by fasterXML.";

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
        System.out.println("[Externalization data from JSON By fasterXML]");
        @Nullable final ObjectMapper objectMapper = new ObjectMapper();
        @Nullable final Collection<Project> projectList;
        @Nullable final Collection<Task> taskList;
        @Nullable final Collection<User> userList;
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final IUserService userService = bootstrap.getUserService();
        try {
            projectList = Arrays.asList(objectMapper
                    .readValue(new File(PROJECTS_FILE_PATH + ".json"), Project[].class));
            taskList = Arrays.asList(objectMapper
                    .readValue(new File(TASKS_FILE_PATH + ".json"), Task[].class));
            userList = Arrays.asList(objectMapper
                    .readValue(new File(USERS_FILE_PATH + ".json"), User[].class));
        } catch (IOException e) {
            return;
        }
        for (@Nullable final Project project : projectList) {
            if (project == null)
                throw new ProjectNotExistsException();
            projectService.persist(project);
        }
        for (@Nullable final Task task : taskList) {
            if (task == null)
                throw new TaskNotExistsException();
            taskService.persist(task);
        }
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
