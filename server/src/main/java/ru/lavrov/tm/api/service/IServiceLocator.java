package ru.lavrov.tm.api.service;

import ru.lavrov.tm.api.service.*;

public interface IServiceLocator {
    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();

    ISessionService getSessionService();

    ITokenService getTokenService();
}
