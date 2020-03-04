package ru.lavrov.tm.api;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.User;

import java.util.List;

public interface ServiceLocator {
    List<AbstractCommand> getCommands();
    ProjectService getProjectService();
    TaskService getTaskService();
    UserService getUserService();
    User getCurrentUser();
    void login(String login, String password);
    void logout();
}
