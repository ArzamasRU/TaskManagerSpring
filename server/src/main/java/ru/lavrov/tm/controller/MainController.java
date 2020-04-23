package ru.lavrov.tm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
    public String greetingByGet() {
    	return "greeting";
    }

    @GetMapping("/registration")
    public String registration() {
	    return "registration";
    }
}
