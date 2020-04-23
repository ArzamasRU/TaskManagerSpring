package ru.lavrov.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.comparator.FinishDateComparator;
import ru.lavrov.tm.comparator.StartDateComparator;
import ru.lavrov.tm.comparator.StatusComparator;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.endpoint.ProjectEndpoint;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static ru.lavrov.tm.util.DateUtil.convertStrToDate;

@Controller
public class ProjectController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @PostMapping("/projectCreation")
    public String createProject(@AuthenticationPrincipal @NotNull final User user,
                                @RequestParam @Nullable final String name,
                                @RequestParam @Nullable final String description,
                                @RequestParam @Nullable final String creationDate,
                                @RequestParam @Nullable final String startDate,
                                @RequestParam @Nullable final String finishDate,
                                @RequestParam(defaultValue = "PLANNED") @Nullable final String status,
                                @Nullable final Model model) throws ParseException {
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        projectService.createProject(realUserId, new Project(name, description, convertStrToDate(creationDate),
                convertStrToDate(startDate), convertStrToDate(finishDate), Status.valueOf(status)));
        model.addAttribute("message", "Project is created!");
        return "/projectCreation";
    }

    @GetMapping("/projectCreation")
    public String projectCreation() {
        return "projectCreation";
    }

    @PostMapping("/removeAllProjects")
    public String createProject(@AuthenticationPrincipal @NotNull final User user, @Nullable final Model model) {
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        projectService.removeAll(realUserId);
        model.addAttribute("projects", new ArrayList());
        return "/projects";
    }

    @GetMapping("/projects")
    public @Nullable String projects(@AuthenticationPrincipal @NotNull final User user,
                                     @RequestParam(required = false, defaultValue = "") @NotNull final String sortKey,
                                     @RequestParam(required = false, defaultValue = "") @NotNull final String searchKey,
                                     @RequestParam(required = false, defaultValue = "") @NotNull final String searchKeyValue,
                                     @Nullable final Model model) throws ParseException {
        if (user == null)
            return null;
        @NotNull final String realUserId = userService.findUserByLogin(user.getLogin()).getId();
        @NotNull final Comparator comparator;
        @Nullable Collection<ProjectDTO> projectList = null;
        if (!sortKey.isEmpty()) {
            switch (sortKey) {
                case "startDate":
                    comparator = new StartDateComparator();
                    projectList = Project.getProjectDTO(projectService.findAll(realUserId, comparator));
                    break;
                case "finishDate":
                    comparator = new FinishDateComparator();
                    projectList = Project.getProjectDTO(projectService.findAll(realUserId, comparator));
                    break;
                case "status":
                    comparator = new StatusComparator();
                    projectList = Project.getProjectDTO(projectService.findAll(realUserId, comparator));
                    break;
            }
        } else if (!searchKey.isEmpty()) {
            switch (searchKey) {
                case "name":
                    projectList = Project.getProjectDTO(
                            projectService.findAllByNamePart(realUserId, searchKeyValue));
                    break;
                case "description":
                    projectList = Project.getProjectDTO(
                            projectService.findAllByDescPart(realUserId, searchKeyValue));
                    break;
            }
        } else {
            projectList = Project.getProjectDTO(projectService.findAll(realUserId));
        }
        model.addAttribute("projects", projectList);
        return "projects";
    }
}
