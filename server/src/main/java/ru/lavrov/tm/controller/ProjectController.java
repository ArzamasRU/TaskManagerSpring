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
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.endpoint.ProjectEndpoint;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.enumerate.Status;
import ru.lavrov.tm.service.UserServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static ru.lavrov.tm.util.DateUtil.convertStrToDate;

@Controller
public class ProjectController {

    @Autowired
    ProjectEndpoint projectEndpoint;

    @PostMapping("/projectCreation/createProject")
    public String createProject(@RequestParam @Nullable final String token,
                                @RequestParam @Nullable final String name,
                                @RequestParam @Nullable final String description,
                                @RequestParam @Nullable final String creationDate,
                                @RequestParam @Nullable final String startDate,
                                @RequestParam @Nullable final String finishDate,
                                @RequestParam(defaultValue = "PLANNED") @Nullable final String status,
                                @Nullable final Model model) throws ParseException {
        projectEndpoint.createProject(token, new Project(name, description, convertStrToDate(creationDate),
                convertStrToDate(startDate), convertStrToDate(finishDate), Status.valueOf(status)));
        model.addAttribute("token", token);
        return "/projects";
    }

    @PostMapping("/projectCreation")
    public String createProject2(@RequestParam @Nullable final String token,
                                 @Nullable final Model model) {
        model.addAttribute("token", token);
        return "projectCreation";
    }

    @PostMapping("/removeAllProjects")
    public String createProject(@RequestParam @Nullable final String token,
                                @Nullable final Model model) {
        projectEndpoint.removeAll(token);
        model.addAttribute("token", token);
        return "/projects";
    }

    @PostMapping("/projects")
    public String projects(@RequestParam @Nullable final String token,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String sortKey,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String searchKey,
                           @RequestParam(required = false, defaultValue = "") @NotNull final String searchKeyValue,
                           @Nullable final Model model) throws ParseException {
        @Nullable Collection<ProjectDTO> projectList = null;
        if (!sortKey.isEmpty()) {
            switch (sortKey) {
                case "startDate":
                    projectList = projectEndpoint.findAllByStartDate(token);
                    break;
                case "finishDate":
                    projectList = projectEndpoint.findAllByFinishDate(token);
                    break;
                case "status":
                    projectList = projectEndpoint.findAllByStatus(token);
                    break;
            }
        } else if (!searchKey.isEmpty()) {
            switch (searchKey) {
                case "name":
                    projectList = projectEndpoint.findAllByNamePart(token, searchKeyValue);
                    break;
                case "description":
                    projectList = projectEndpoint.findAllByDescPart(token, searchKeyValue);
                    break;
            }
        } else {
            projectList = projectEndpoint.findAll(token);
        }
        model.addAttribute("projects", projectList);
        model.addAttribute("token", token);
        return "projects";
    }
}
