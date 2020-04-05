package ru.lavrov.tm.integrationTest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.category.FirstCategory;
import ru.lavrov.tm.endpoint.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Category(FirstCategory.class)
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
    private final String login = "testAdmin";

    @NotNull
    private final String password = "testAdmin";

    @NotNull
    private final String role = "admin";

    @NotNull
    private final String testProjectName = "testProject";

    @NotNull
    private final String testTaskName = "testTask";

    @BeforeEach
    void init() {
        boolean success;
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
        @Nullable final String hashedPassword = md5Hard(password);
        assertNotNull(hashedPassword);

        token = tokenEndpoint.login(login, hashedPassword);
        if (token != null) {
            @Nullable final Collection<Task> taskList = taskEndpoint.findAllTasks(token);
            if (taskList != null || !taskList.isEmpty()) {
                success = taskEndpoint.removeAll(token);
                assertTrue(success);
            }
            @Nullable final Collection<Project> projectList = projectEndpoint.findAll(token);
            if (projectList != null || !projectList.isEmpty()) {
                success = projectEndpoint.removeAll(token);
                assertTrue(success);
            }
            success = userEndpoint.deleteUser(token);
            assertTrue(success);
        }

        success = userEndpoint.registerUser(login, hashedPassword, role);
        assertTrue(success);
        token = tokenEndpoint.login(login, hashedPassword);
        assertNotNull(token);
    }

    @AfterEach
    void close() {
        boolean success = userEndpoint.deleteUser(token);
        assertTrue(success);
    }

    @Test
    void getUserProjectsTest() {
        boolean success = projectEndpoint.createByProjectName(token, testProjectName);
        assertTrue(success);
        @Nullable final Collection<Project> projectList = userEndpoint.getUserProjects(token);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final Project project : projectList) {
            assertNotNull(project);
        }
        success = projectEndpoint.removeProjectByName(token, testProjectName);
        assertTrue(success);
    }

    @Test
    void getUserTasksTest() {
        boolean success = projectEndpoint.createByProjectName(token, testProjectName);
        assertTrue(success);
        success = taskEndpoint.createByTaskName(token, testTaskName, testProjectName);
        assertTrue(success);
        @Nullable final Collection<Task> taskList =  userEndpoint.getUserTasks(token);
        assertNotNull(taskList);
        assertFalse(taskList.isEmpty());
        for (@Nullable final Task task : taskList) {
            assertNotNull(task);
        }
        success = taskEndpoint.removeTaskByName(token, testTaskName);
        assertTrue(success);
        success = projectEndpoint.removeProjectByName(token, testProjectName);
        assertTrue(success);
    }

    @Test
    void getUserBadTest() {
        @Nullable final User user = userEndpoint.getUser(token);
        assertNotNull(user);
    }

    public String getToken() {
        return token;
    }
}
