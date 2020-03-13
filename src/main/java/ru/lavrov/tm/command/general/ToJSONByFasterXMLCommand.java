package ru.lavrov.tm.command.general;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.constant.Constant.*;


public class ToJSONByFasterXMLCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "to-JSON-by-fasterXML";
    @NotNull
    private static final String DESCRIPTION = "Externalize data to JSON by fasterXML.";

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
        System.out.println("[Externalization data to JSON By fasterXML]");
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Collection<Project> projectList = projectService.findAll(currentUser.getId());
        @NotNull final Collection<Task> taskList = taskService.findAll(currentUser.getId());

        try{
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(USERS_FILE_PATH + ".json"), currentUser);
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(PROJECTS_FILE_PATH + ".json"), projectList);
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(TASKS_FILE_PATH + ".json"), taskList);
        } catch (IOException e) {
            return;
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
