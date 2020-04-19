package ru.lavrov.tm.controller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.endpoint.ProjectEndpoint;

import java.util.Collection;

@Controller
public class MainController {

	@GetMapping("/")
	public String greeting(@Nullable final Model model) {
		return "greeting";
	}

	@GetMapping("/login")
	public String login(@Nullable final Model model) { return "login"; }

	@GetMapping("/registration")
	public String registration(@Nullable final Model model) {
		return "registration";
	}
}
