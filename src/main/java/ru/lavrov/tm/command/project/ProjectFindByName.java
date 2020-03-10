package ru.lavrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public final class ProjectFindByName extends AbstractCommand {
    private static final boolean SAFE = false;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
    @NotNull
    private static final String COMMAND = "project-find-by-name";
    @NotNull
    private static final String DESCRIPTION = "Show project by name.";

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
        @Nullable final Scanner input = new Scanner(System.in);
        System.out.println("[PROJECT LIST]");
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final User currentUser = bootstrap.getCurrentUser();
        if (currentUser == null)
            throw new UserIsNotAuthorizedException();
        System.out.println("enter name:");
        @Nullable final String name = input.nextLine();
        @Nullable final Collection<Project> projectList = projectService.findAllByNamePart(name, currentUser.getId());
        if (projectList == null)
            return;
        int index = 1;
        for (@Nullable final IEntity project: projectList) {
            if (project == null)
                continue;
            System.out.println(index++ + ". " + project);
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
