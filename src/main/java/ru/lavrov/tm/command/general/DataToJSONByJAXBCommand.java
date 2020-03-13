package ru.lavrov.tm.command.general;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.ExternalizationStorage;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.constant.Constant.EXTERNALIZATION_DIR_PATH;
import static ru.lavrov.tm.util.JAXBUtil.*;

public final class DataToJSONByJAXBCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "to-JSON-by-JAXB";
    @NotNull
    private static final String DESCRIPTION = "Externalize data to JSON by JAXB.";

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
        System.out.println("[Externalization data to JSON By JAXB]");
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        @NotNull final ExternalizationStorage storage = new ExternalizationStorage();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Collection<Project> projectList = projectService.findAll(currentUser.getId());
        if (projectList == null)
            return;;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Collection<Task> taskList = taskService.findAll(currentUser.getId());
        if (taskList == null)
            return;;
        storage.setProjectList(projectList);
        storage.setTaskList(taskList);
        storage.setUserList(Arrays.asList(currentUser));
        @NotNull final String filePath =
                EXTERNALIZATION_DIR_PATH + ExternalizationStorage.class.getSimpleName() + ".json";
        writeToJSONByJAXB(storage, filePath);
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
