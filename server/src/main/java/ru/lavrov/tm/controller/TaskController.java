package ru.lavrov.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.endpoint.TaskEndpoint;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.enumerate.Status;

import java.text.ParseException;
import java.util.Collection;

import static ru.lavrov.tm.util.DateUtil.convertStrToDate;

@Controller
public class TaskController {
    @Autowired
    TaskEndpoint taskEndpoint;

    @PostMapping("/taskCreation")
    public String createTask(@RequestParam @Nullable final String token,
                                @RequestParam @Nullable final String name,
                                @RequestParam @Nullable final String description,
                                @RequestParam @Nullable final String creationDate,
                                @RequestParam @Nullable final String startDate,
                                @RequestParam @Nullable final String finishDate,
                                @RequestParam(defaultValue = "PLANNED") @Nullable final String status,
                                @RequestParam @Nullable final String projectName,
                                @Nullable final Model model) throws ParseException {
        taskEndpoint.createTask(token, new Task(name, description, convertStrToDate(creationDate),
                convertStrToDate(startDate), convertStrToDate(finishDate), Status.valueOf(status)), projectName);
        return "/tasks";
    }

    @GetMapping("/taskCreation")
    public String createTask2(@Nullable final Model model) {
        return "taskCreation";
    }

    @PostMapping("/removeAllTasks")
    public String createTask(@RequestParam @Nullable final String token,
                                @Nullable final Model model) {
        taskEndpoint.removeAll(token);
        return "/tasks";
    }

    @PostMapping("/tasks")
    public String tasks(@RequestParam @Nullable final String token,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String sortKey,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String searchKey,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String searchKeyValue,
                           @Nullable final Model model) {
        @Nullable Collection<TaskDTO> taskList = null;
        if (!sortKey.isEmpty()) {
            switch (sortKey) {
                case "startDate":
                    taskList = taskEndpoint.findAllTasksByStartDate(token);
                    break;
                case "finishDate":
                    taskList = taskEndpoint.findAllTasksByFinishDate(token);
                    break;
                case "status":
                    taskList = taskEndpoint.findAllTasksByStatus(token);
                    break;
            }
        } else if (!searchKey.isEmpty()) {
            switch (searchKey) {
                case "name":
                    taskList = taskEndpoint.findAllTasksByNamePart(token, searchKeyValue);
                    break;
                case "description":
                    taskList = taskEndpoint.findAllTasksByDescPart(token, searchKeyValue);
                    break;
            }
        } else {
            taskList = taskEndpoint.findAllTasks(token);
        }
        model.addAttribute("tasks", taskList);
        return "tasks";
    }
}
