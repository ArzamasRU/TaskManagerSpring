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
public class ProjectEndpointTest {

    @NotNull
    private final Bootstrap bootstrap = new Bootstrap();

    @NotNull
    private final UserEndpointTest userEndpointTest = new UserEndpointTest();

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
    private final String testProjectName = "testProject";

    @NotNull
    private final String testTaskName = "testTask";

    @BeforeEach
    void init() {
        boolean success;
        userEndpointTest.init();
        token = userEndpointTest.getToken();
        success = projectEndpoint.createByProjectName(token, testProjectName);
        assertTrue(success);

        @Nullable final Collection<Project> projectList = userEndpoint.getUserProjects(token);
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
        for (@Nullable final Project project : projectList) {
            assertNotNull(project);
        }
    }

    @AfterEach
    void close() {
        userEndpointTest.close();
        boolean success = projectEndpoint.removeProjectByName(token, testProjectName);
        assertTrue(success);
    }

}
