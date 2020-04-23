package ru.lavrov.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.api.service.ITaskService;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.endpoint.TaskEndpoint;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static ru.lavrov.tm.util.DateUtil.convertStrToDate;

@Controller
public class TaskController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;

    @PostMapping("/taskCreation")
    public String createTask(@AuthenticationPrincipal @NotNull final User user,
                             @RequestParam @Nullable final String name,
                             @RequestParam @Nullable final String description,
                             @RequestParam @Nullable final String projectName,
                             @RequestParam @Nullable final String creationDate,
                             @RequestParam @Nullable final String startDate,
                             @RequestParam @Nullable final String finishDate,
                             @RequestParam(defaultValue = "PLANNED") @Nullable final String status,
                             @Nullable final Model model) throws ParseException {
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        taskService.createTask(realUserId, new Task(name, description, convertStrToDate(creationDate),
                convertStrToDate(startDate), convertStrToDate(finishDate), Status.valueOf(status)), projectName);
        model.addAttribute("message", "Task is created!");
        return "/taskCreation";
    }

    @GetMapping("/taskCreation")
    public String taskCreation() {
        return "taskCreation";
    }

    @PostMapping("/removeAllTasks")
    public String createTask(@AuthenticationPrincipal @NotNull final User user, @Nullable final Model model) {
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        taskService.removeAll(realUserId);
        model.addAttribute("tasks", new ArrayList());
        return "/tasks";
    }

    @GetMapping("/tasks")
    public @Nullable String tasks(@AuthenticationPrincipal @NotNull final User user,
                                  @RequestParam(required = false, defaultValue = "") @NotNull final String sortKey,
                                  @RequestParam(required = false, defaultValue = "") @NotNull final String searchKey,
                                  @RequestParam(required = false, defaultValue = "") @NotNull final String searchKeyValue,
                                  @Nullable final Model model) throws ParseException {
        if (user == null)
            return null;
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        @NotNull final Comparator comparator;
        @Nullable Collection<TaskDTO> taskList = null;
        if (!sortKey.isEmpty()) {
            switch (sortKey) {
                case "startDate":
                    comparator = new StartDateComparator();
                    taskList = Task.getTaskDTO(taskService.findAll(realUserId, comparator));
                    break;
                case "finishDate":
                    comparator = new FinishDateComparator();
                    taskList = Task.getTaskDTO(taskService.findAll(realUserId, comparator));
                    break;
                case "status":
                    comparator = new StatusComparator();
                    taskList = Task.getTaskDTO(taskService.findAll(realUserId, comparator));
                    break;
            }
        } else if (!searchKey.isEmpty()) {
            switch (searchKey) {
                case "name":
                    taskList = Task.getTaskDTO(
                            taskService.findAllByNamePart(realUserId, searchKeyValue));
                    break;
                case "description":
                    taskList = Task.getTaskDTO(
                            taskService.findAllByDescPart(realUserId, searchKeyValue));
                    break;
            }
        } else {
            taskList = Task.getTaskDTO(taskService.findAll(realUserId));
        }
        model.addAttribute("tasks", taskList);
        return "tasks";
    }
}
