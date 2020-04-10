package ru.lavrov.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.endpoint.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Category(ru.lavrov.tm.ProjectTestCategory.class)
public class ProjectEndpointTest extends Assert {

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
        for (@Nullable final ProjectDTO currProject : projectList) {
            assertNotNull(currProject);
        }
    }

    @AfterEach
    void close() {
        @Nullable final ProjectDTO project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        if (project != null) {
            projectEndpoint.removeProjectByName(token, TEST_PROJECT_NAME);
        }
        userEndpoint.deleteUser(token);
    }

    @Test
    void removeTest() {
        @Nullable ProjectDTO project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        assertNotNull(project);
        projectEndpoint.remove(token, project.getId());
        project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        assertNull(project);
    }

    @Test
    void getProjectTasksTest() {
        taskEndpoint.createByTaskName(token, TEST_TASK_NAME, TEST_PROJECT_NAME);
        @Nullable final Collection<TaskDTO> taskList =  projectEndpoint.getProjectTasks(token, TEST_PROJECT_NAME);
        assertNotNull(taskList);
        assertFalse(taskList.isEmpty());
        for (@Nullable final TaskDTO task : taskList) {
            assertNotNull(task);
        }
        taskEndpoint.removeTaskByName(token, TEST_TASK_NAME);
    }

    @Test
    void renameProjectTest() {
        @NotNull final String newName = "newName";
        projectEndpoint.renameProject(token, TEST_PROJECT_NAME, newName);
        @Nullable ProjectDTO project = projectEndpoint.findProjectByName(token, newName);
        assertNotNull(project);
        assertNotEquals(project.getName(), TEST_PROJECT_NAME);
        project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        assertNull(project);
    }

    @Test
    void findAllByNamePartTest() {
        @NotNull final String croppedProjectName = TEST_PROJECT_NAME.substring(2);
        @Nullable final Collection<ProjectDTO> projectList = projectEndpoint.findAllByNamePart(token, croppedProjectName);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final ProjectDTO project : projectList) {
            assertNotNull(project);
        }
    }

    @Test
    void findAllByStatusTest() {
        @Nullable final Collection<ProjectDTO> projectList = projectEndpoint.findAllByStatus(token);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final ProjectDTO project : projectList) {
            assertNotNull(project);
        }
    }

    @Test
    void removeAllTest() {
        projectEndpoint.removeAll(token);
        @Nullable ProjectDTO project = projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME);
        assertNull(project);
    }

    @Test
    void findProjectByNameBadTest() {
//                @NotNull final Executable doLogin = new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                token = tokenEndpoint.login(LOGIN, hashedPassword);
//            }
//        };
//        assertThrows(ClientTransportException.class, doLogin);
        @Nullable final ProjectDTO projectList =
                projectEndpoint.findProjectByName(token, TEST_PROJECT_NAME.substring(2));
    }
}
