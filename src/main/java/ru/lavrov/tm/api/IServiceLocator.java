package ru.lavrov.tm.api;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;

import java.util.List;

public interface IServiceLocator {
    List<AbstractCommand> getCommands();
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    User getCurrentUser();
    void login(String login, String password);
    void logout();
}
