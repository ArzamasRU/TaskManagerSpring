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

import static org.junit.jupiter.api.Assertions.*;
import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Category(ru.lavrov.tm.TaskTestCategory.class)
public class TaskEndpointTest {

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
    private static String PASSWORD = "testAdmin";

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

        @Nullable final ProjectDTO project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        if (project == null) {
            projectEndpoint.createByProjectName(token, TEST_PROJECT_NAME);
        }
        @Nullable final Collection<ProjectDTO> projectList = userEndpoint.getUserProjects(token);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final ProjectDTO currProject : projectList)
            assertNotNull(currProject);

        @Nullable TaskDTO task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        if (task == null) {
            taskEndpoint.createByTaskName(token, TEST_TASK_NAME, TEST_PROJECT_NAME);
        }
        task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNotNull(task);
    }

    @AfterEach
    void close() {
        
        @Nullable final TaskDTO task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        if (task != null) {
            taskEndpoint.removeTaskByName(token, TEST_TASK_NAME);
        }
        @Nullable final ProjectDTO project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        if (project != null) {
            projectEndpoint.removeProjectByName(token, TEST_PROJECT_NAME);
        }
        userEndpoint.deleteUser(token);
    }

    @Test
    @Category(TaskTestCategory.class)
    void removeTest() {
        @Nullable TaskDTO task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNotNull(task);
        taskEndpoint.remove(token, task.getId());
        task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNull(task);
    }


    @Test
    void renameTaskTest() {
        @NotNull final String newName = "newName";
        taskEndpoint.renameTask(token, TEST_PROJECT_NAME, TEST_TASK_NAME, newName);
        @Nullable TaskDTO task = taskEndpoint.findTaskByName(token, newName);
        assertNotNull(task);
        assertNotEquals(task.getName(), TEST_TASK_NAME);
        task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNull(task);
    }

    @Test
    void findAllByNamePartTest() {
        @NotNull final String croppedTaskName = TEST_TASK_NAME.substring(2);
        @Nullable final Collection<TaskDTO> taskList = taskEndpoint.findAllTasksByNamePart(token, croppedTaskName);
        assertNotNull(taskList);
        assertFalse(taskList.isEmpty());
        for (@Nullable final TaskDTO task : taskList) {
            assertNotNull(task);
        }
    }

    @Test
    void findAllByStatusTest() {
        @Nullable final Collection<TaskDTO> taskList = taskEndpoint.findAllTasksByStatus(token);
        assertNotNull(taskList);
        assertFalse(taskList.isEmpty());
        for (@Nullable final TaskDTO task : taskList) {
            assertNotNull(task);
        }
    }

    @Test
    void removeAllTest() {
        taskEndpoint.removeAll(token);
        @Nullable TaskDTO task = taskEndpoint.findTaskByName(token, TEST_TASK_NAME);
        assertNull(task);
    }
}
