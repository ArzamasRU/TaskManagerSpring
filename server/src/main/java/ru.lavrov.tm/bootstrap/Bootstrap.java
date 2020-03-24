package ru.lavrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.repository.ProjectRepositoryImpl;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;
import ru.lavrov.tm.service.ProjectServiceImpl;
import ru.lavrov.tm.service.SessionServiceImpl;
import ru.lavrov.tm.service.TaskServiceImpl;
import ru.lavrov.tm.service.UserServiceImpl;

import javax.xml.ws.Endpoint;

@Getter
@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {
    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepositoryImpl();
    @NotNull
    private final ITaskRepository taskRepository = new TaskRepositoryImpl();
    @NotNull
    private final IUserRepository userRepository = new UserRepositoryImpl();
    @NotNull
    private final IProjectService projectService =
            new ProjectServiceImpl(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskServiceImpl(taskRepository, projectRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserServiceImpl(userRepository, projectRepository, taskRepository);
    @NotNull
    private final ISessionService sessionService = new SessionServiceImpl(userRepository);
    @NotNull
    private final UserEndpoint userEndpoint = new UserEndpoint(this);
    @NotNull
    private final SessionEndpoint sessionEndpoint = new SessionEndpoint(this);
    @NotNull
    private final ProjectEndpoint projectEndpoint = new ProjectEndpoint(this);
    @NotNull
    private final TaskEndpoint taskEndpoint = new TaskEndpoint(this);
    @NotNull
    private final GeneralCommandsEndpoint generalCommandsEndpoint = new GeneralCommandsEndpoint(this);

    public void init() {
        initProperties();
        initUsers();
        initEndpoints();
        System.out.println("*** SERVER STARTED ***");
    }

    private void initEndpoints() {
        Endpoint.publish(UserEndpoint.URL, userEndpoint);
        Endpoint.publish(SessionEndpoint.URL, sessionEndpoint);
        Endpoint.publish(ProjectEndpoint.URL, projectEndpoint);
        Endpoint.publish(TaskEndpoint.URL, taskEndpoint);
        Endpoint.publish(GeneralCommandsEndpoint.URL, generalCommandsEndpoint);
    }


    private void initUsers() {
        userService.createByLogin("user", "user", Role.USER.getRole());
        userService.createByLogin("admin", "admin", Role.ADMIN.getRole());
    }

    private void initProperties() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }
}

