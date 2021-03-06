package ru.lavrov.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.endpoint.*;

import java.util.Collection;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Category(ru.lavrov.tm.UserTestCategory.class)
public class UserEndpointTest {

    @NotNull
    private final Bootstrap bootstrap = new Bootstrap();

    @NotNull
    private UserEndpoint userEndpoint;

    @NotNull
    private TokenEndpoint tokenEndpoint;

    @NotNull
    private ProjectEndpoint projectEndpoint;

    @NotNull
    private TaskEndpoint taskEndpoint;

    @Nullable
    private String token;

    @NotNull
    private static final String LOGIN = "testAdmin";

    @NotNull
    private static final String PASSWORD = "testAdmin";

    @NotNull
    private static final String ROLE = "admin";

    @NotNull
    private static final String TEST_PROJECT_NAME = "testProject";

    @NotNull
    private static final String TEST_TASK_NAME = "testTask";

    @BeforeEach
    void init() {
        bootstrap.init();
        @NotNull final UserEndpointService userEndpointService = bootstrap.getUserEndpointService();
        assertNotNull(userEndpointService);
        userEndpoint = userEndpointService.getUserEndpointPort();
        assertNotNull(userEndpoint);
        @NotNull final TokenEndpointService tokenEndpointService = bootstrap.getTokenEndpointService();
        assertNotNull(tokenEndpointService);
        tokenEndpoint = tokenEndpointService.getTokenEndpointPort();
        assertNotNull(tokenEndpoint);
        @NotNull final ProjectEndpointService projectEndpointService = bootstrap.getProjectEndpointService();
        assertNotNull(projectEndpointService);
        projectEndpoint = projectEndpointService.getProjectEndpointPort();
        assertNotNull(projectEndpoint);
        @NotNull final TaskEndpointService taskEndpointService = bootstrap.getTaskEndpointService();
        assertNotNull(taskEndpointService);
        taskEndpoint = taskEndpointService.getTaskEndpointPort();
        assertNotNull(taskEndpoint);
        @Nullable final String hashedPassword = md5Hard(PASSWORD);
        assertNotNull(hashedPassword);

        token = tokenEndpoint.login(LOGIN, hashedPassword);
        if (token != null) {
            @Nullable final Collection<TaskDTO> taskList = taskEndpoint.findAllTasks(token);
            if (taskList != null || !taskList.isEmpty()) {
                taskEndpoint.removeAll(token);
            }
            @Nullable final Collection<ProjectDTO> projectList = projectEndpoint.findAll(token);
            if (projectList != null || !projectList.isEmpty()) {
                projectEndpoint.removeAll(token);
            }
            userEndpoint.deleteUser(token);
        }

        userEndpoint.registerUser(LOGIN, hashedPassword, ROLE);
        token = tokenEndpoint.login(LOGIN, hashedPassword);
        assertNotNull(token);
    }

    @AfterEach
    void close() {
        @Nullable final UserDTO user = userEndpoint.getUser(token);
        if (user != null)
            userEndpoint.deleteUser(token);
    }

    @Test
    void getUserProjectsTest() {
        projectEndpoint.createByProjectName(token, TEST_PROJECT_NAME);
        @Nullable final Collection<ProjectDTO> projectList = userEndpoint.getUserProjects(token);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final ProjectDTO project : projectList)
            assertNotNull(project);
        projectEndpoint.removeProjectByName(token, TEST_PROJECT_NAME);
    }

    @Test
    void getUserTasksTest() {
        projectEndpoint.createByProjectName(token, TEST_PROJECT_NAME);
        taskEndpoint.createByTaskName(token, TEST_TASK_NAME, TEST_PROJECT_NAME);
        @Nullable final Collection<TaskDTO> taskList =  userEndpoint.getUserTasks(token);
        assertNotNull(taskList);
        assertFalse(taskList.isEmpty());
        for (@Nullable final TaskDTO task : taskList) {
            assertNotNull(task);
        }
        taskEndpoint.removeTaskByName(token, TEST_TASK_NAME);
        projectEndpoint.removeProjectByName(token, TEST_PROJECT_NAME);
    }

    @Test
    void getUserTest() {
        @Nullable final UserDTO user = userEndpoint.getUser(token);
        assertNotNull(user);
    }

    @Test
    void removeByCascadeTest() {
        projectEndpoint.createByProjectName(token, TEST_PROJECT_NAME);
        taskEndpoint.createByTaskName(token, TEST_TASK_NAME, TEST_PROJECT_NAME);
        userEndpoint.deleteUser(token);
        @Nullable final TaskDTO task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNull(task);
        @Nullable final ProjectDTO project =  projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        assertNull(project);
    }
}
