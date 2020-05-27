package ru.lavrov.tm.restcontroller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

@RestController
@RequestMapping(value = "/rest/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/createByProjectName")
    public void createByProjectName(@RequestParam("userId") @Nullable final String userId,
                                    @RequestParam("projectName") @Nullable final String projectName) {
    }

    @PostMapping(value = "/persist")
    public void persist(@RequestBody @Nullable final ProjectDTO projectDTO) {
        projectService.persist(Project.getProjectFromDTO(projectDTO));
    }

    @PostMapping(value = "/merge")
    public void merge(@RequestBody @Nullable final ProjectDTO projectDTO) {
        projectService.merge(Project.getProjectFromDTO(projectDTO));
    }

    @GetMapping(value = "/removeProjectByName")
    public void removeProjectByName(@RequestParam("userId") @Nullable final String userId,
                                    @RequestParam("projectName") @Nullable final String projectName) {
        projectService.removeProjectByName(userId, projectName);
    }

    @GetMapping(value = "/removeProject")
    public void removeProject(@RequestParam("userId") @Nullable final String userId,
                              @RequestParam("projectId") @Nullable final String projectId) {
        projectService.removeProject(userId, projectId);
    }

    @GetMapping(value = "/removeAll/{userId}")
    public void removeAll(@PathVariable("userId") @Nullable final String userId) {
        projectService.removeAll(userId);
    }

    @GetMapping(value = "/getProjectTasks")
    public @Nullable Collection<TaskDTO> getProjectTasks(@RequestParam("login") @Nullable final String userId,
                                                         @RequestParam("login") @Nullable final String projectName) {
        return Task.getTaskDTO(projectService.getProjectTasks(userId, projectName));
    }

    @GetMapping(value = "/findAllByNamePart")
    public @Nullable Collection<ProjectDTO> findAllByNamePart(@RequestParam("userId") @Nullable final String userId,
                                                              @RequestParam("name") @Nullable final String name) {
        return Project.getProjectDTO(projectService.findAllByNamePart(userId, name));
    }

    @GetMapping(value = "/findAllByDescPart")
    public @Nullable Collection<ProjectDTO> findAllByDescPart(
            @RequestParam("userId") @Nullable final String userId,
            @RequestParam("description") @Nullable final String description
    ) {
        return Project.getProjectDTO(projectService.findAllByDescPart(userId, description));
    }

    @GetMapping(value = "/findProjectByName")
    public @Nullable ProjectDTO findProjectByName(@RequestParam("userId") @Nullable final String userId,
                                                  @RequestParam("projectName") @Nullable final String projectName) {
        return Project.getProjectDTO(projectService.findProjectByName(userId, projectName));
    }

    @GetMapping(value = "/findAll/{userId}")
    public @Nullable Collection<ProjectDTO> findAll(@PathVariable("userId") @Nullable final String userId) {
        return Project.getProjectDTO(projectService.findAll(userId));
    }

    @GetMapping(value = "/findOne")
    public @Nullable ProjectDTO findOne(@RequestParam("userId") @Nullable final String userId,
                                        @RequestParam("entityId") @Nullable final String entityId) {
        return Project.getProjectDTO(projectService.findOne(userId, entityId));
    }
}
