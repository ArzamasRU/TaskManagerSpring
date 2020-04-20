package ru.lavrov.tm.controller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.endpoint.ProjectEndpoint;
import ru.lavrov.tm.endpoint.TokenEndpoint;
import ru.lavrov.tm.endpoint.UserEndpoint;
import ru.lavrov.tm.entity.Token;
import ru.lavrov.tm.entity.User;

import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Controller
public class UserController {

    @Autowired
    TokenEndpoint tokenEndpoint;

    @Autowired
    UserEndpoint userEndpoint;

    @PostMapping("/login/signIn")
    public String greeting(@RequestParam @Nullable final String token,
                           @RequestParam @Nullable final String login,
                           @RequestParam @Nullable final String password,
                           @Nullable final Model model) {
        @Nullable final String hashedPassword = md5Hard(password);
        @Nullable final String newToken = tokenEndpoint.login(login, hashedPassword);
        if (newToken == null || newToken.isEmpty()) {
            model.addAttribute("message", "Login or password is incorrect!");
            model.addAttribute("token", token);
        } else {
            model.addAttribute("token", newToken);
            model.addAttribute("message", "You are logged in!");
        }
        return "greeting";
    }

    @PostMapping("/registration/register")
    public String signIn(@RequestParam @Nullable final String token,
                         @RequestParam @Nullable final String login,
                         @RequestParam @Nullable final String password,
                         @RequestParam @Nullable final String role,
                         @Nullable final Model model) {
        try {
            userEndpoint.registerUser(login, password, role);
            model.addAttribute("message", "Registration is successful!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Registration is failed!");
        }
        model.addAttribute("token", token);
        return "greeting";
    }

    @GetMapping("/logout")
    public String logout(@Nullable final Model model) {
        model.addAttribute("token", "");
        model.addAttribute("login", "");
        return "greeting";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam @Nullable final String token,
                             @Nullable final Model model) {
        userEndpoint.deleteUser(token);
        model.addAttribute("token", "");
        model.addAttribute("login", "");
        return "greeting";
    }

    @PostMapping("/account")
    public String account(@RequestParam @Nullable final String token,
                          @Nullable final Model model) {
        model.addAttribute("token", token);
        return "account";
    }
}
