package ru.lavrov.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.general.AboutCommand;
import ru.lavrov.tm.command.general.ExitCommand;
import ru.lavrov.tm.command.general.HelpCommand;
import ru.lavrov.tm.command.project.*;
import ru.lavrov.tm.command.task.*;
import ru.lavrov.tm.command.user.*;

import java.util.Arrays;
import java.util.Collection;

public final class Application {
    @Nullable
    private static final Collection<Class> CLASSES = Arrays.asList(ExitCommand.class,
            HelpCommand.class,
            AboutCommand.class,
            ProjectClearCommand.class,
            ProjectCreateCommand.class,
            ProjectListCommand.class,
            ProjectRemoveCommand.class,
            ProjectTasksListCommand.class,
            ProjectRenameCommand.class,
            TaskClearCommand.class,
            TaskCreateCommand.class,
            TaskListCommand.class,
            TaskRemoveCommand.class,
            TaskRenameCommand.class,
            UserLoginCommand.class,
            UserLogoutCommand.class,
            UserRegisterCommand.class,
            UserUpdateCommand.class,
            UserDisplayCommand.class,
            UserDeleteCommand.class);

    public static void main(@Nullable String[] args) throws IllegalAccessException, InstantiationException {
        @NotNull Bootstrap serviceLocator = new Bootstrap();
        serviceLocator.init(CLASSES);
    }
}