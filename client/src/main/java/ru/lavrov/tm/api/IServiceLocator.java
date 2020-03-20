package ru.lavrov.tm.api;

public interface IServiceLocator {
    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();
}
