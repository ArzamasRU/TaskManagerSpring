package ru.lavrov.tm.controller;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
    public String greetingByGet() {
    	return "greeting";
    }

	@PostMapping("/")
	public String greetingByPost(@RequestParam @Nullable final String token,
						   @Nullable final Model model) {
		model.addAttribute("token", token);
		return "greeting";
	}

    @PostMapping("/login")
    public String login(@RequestParam @Nullable final String token,
                        @Nullable final Model model) {
		model.addAttribute("token", token);
    	return "login";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam @Nullable final String token,
							   @Nullable final Model model) {
		model.addAttribute("token", token);
    	return "registration";
    }
}
