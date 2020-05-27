package ru.lavrov.tm.restcontroller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lavrov.tm.api.service.ITaskService;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;

@RestController
@RequestMapping(value = "/rest/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {

    @Autowired
    private ITaskService taskService;

    @GetMapping(value = "/createByTaskName")
    public void createByTaskName(@RequestParam("userId") @Nullable final String userId,
                                 @RequestParam("taskName") @Nullable final String taskName) {
    }

    @PostMapping(value = "/persist")
    public void persist(@RequestBody @Nullable final TaskDTO taskDTO) {
        taskService.persist(Task.getTaskFromDTO(taskDTO));
    }

    @PostMapping(value = "/merge")
    public void merge(@RequestBody @Nullable final TaskDTO taskDTO) {
        taskService.merge(Task.getTaskFromDTO(taskDTO));
    }

    @GetMapping(value = "/removeTaskByName")
    public void removeTaskByName(@RequestParam("userId") @Nullable final String userId,
                                 @RequestParam("taskName") @Nullable final String taskName) {
        taskService.removeTaskByName(userId, taskName);
    }

    @GetMapping(value = "/removeTask")
    public void removeTask(@RequestParam("userId") @Nullable final String userId,
                           @RequestParam("taskId") @Nullable final String taskId) {
        taskService.removeTask(userId, taskId);
    }

    @GetMapping(value = "/removeAll/{userId}")
    public void removeAll(@PathVariable("userId") @Nullable final String userId) {
        taskService.removeAll(userId);
    }

    @GetMapping(value = "/findAllByNamePart")
    public @Nullable Collection<TaskDTO> findAllByNamePart(@RequestParam("userId") @Nullable final String userId,
                                                           @RequestParam("name") @Nullable final String name) {
        return Task.getTaskDTO(taskService.findAllByNamePart(userId, name));
    }

    @GetMapping(value = "/findAllByDescPart")
    public @Nullable Collection<TaskDTO> findAllByDescPart(
            @RequestParam("userId") @Nullable final String userId,
            @RequestParam("description") @Nullable final String description
    ) {
        return Task.getTaskDTO(taskService.findAllByDescPart(userId, description));
    }

    @GetMapping(value = "/findTaskByName")
    public @Nullable TaskDTO findTaskByName(@RequestParam("userId") @Nullable final String userId,
                                            @RequestParam("taskName") @Nullable final String taskName) {
        return Task.getTaskDTO(taskService.findTaskByName(userId, taskName));
    }

    @GetMapping(value = "/findAll/{userId}")
    public @Nullable Collection<TaskDTO> findAll(@PathVariable("userId") @Nullable final String userId) {
        return Task.getTaskDTO(taskService.findAll(userId));
    }
}
