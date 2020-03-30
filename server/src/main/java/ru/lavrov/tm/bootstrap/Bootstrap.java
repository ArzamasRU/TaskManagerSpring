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
import ru.lavrov.tm.util.ConnectionUtil;

import javax.xml.ws.Endpoint;
import java.sql.Connection;

@Getter
@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {
    @NotNull
    private final Connection connection = ConnectionUtil.getConnection();
    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
    @NotNull
    private final ITaskRepository taskRepository = new TaskRepositoryImpl(connection);
    @NotNull
    private final IUserRepository userRepository = new UserRepositoryImpl(connection);
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
    private final GeneralCommandEndpoint generalCommandEndpoint = new GeneralCommandEndpoint(this);

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
        Endpoint.publish(GeneralCommandEndpoint.URL, generalCommandEndpoint);
    }

    private void initUsers() {
        if (userService.findUserByLogin("user") == null)
            userService.createByLogin("user", "user", Role.USER.getRole());
        if (userService.findUserByLogin("admin") == null)
            userService.createByLogin("admin", "admin", Role.ADMIN.getRole());
    }

    private void initProperties() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }
}

