package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;

import java.util.List;

public interface IServiceLocator {
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
}
