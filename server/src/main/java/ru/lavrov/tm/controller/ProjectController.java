package ru.lavrov.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.service.UserServiceImpl;

@Controller
public class ProjectController {

    @Autowired
    IUserService userService;

    @GetMapping("/main")
    public String test(Model model) throws Exception {
        System.out.println(userService.findUserByLogin("admin"));
        return "main";
    }

    @GetMapping("/gr")
    public String test2(Model model) throws Exception {
        return "main";
    }
}
