package ru.lavrov.tm.command.general;

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

import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.constant.Constant.EXTERNALIZATION_DIR_PATH;
import static ru.lavrov.tm.util.JAXBUtil.writeToXMLByJAXB;

public final class DataToXMLByJAXBСommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "to-XML-by-JAXB";
    @NotNull
    private static final String DESCRIPTION = "Externalize data to XML by JAXB.";

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
        System.out.println("[Externalization data to XML By JAXB]");
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        writeToXMLByJAXB(Arrays.asList(currentUser), EXTERNALIZATION_DIR_PATH);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Collection<Project> projectList = projectService.findAll(currentUser.getId());
        if (projectList == null)
            return;
        writeToXMLByJAXB(projectList, EXTERNALIZATION_DIR_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Task> taskList = taskService.findAll(currentUser.getId());
        if (taskList == null)
            return;
        writeToXMLByJAXB(taskList, EXTERNALIZATION_DIR_PATH);
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
