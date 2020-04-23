package ru.lavrov.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavrov.tm.api.repository.IUserRepository;
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
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public @NotNull String signIn(@Nullable final User user, @Nullable final Model model) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.create(user);
            model.addAttribute("message", "Registration is successful!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Registration is failed!");
        }
        return "redirect:/login";
    }

    @PostMapping("/deleteUser")
    public @Nullable String deleteUser(@AuthenticationPrincipal @NotNull final User user) {
        if (user == null)
            return null;
        userService.removeUser(userService.findUserByLogin(user.getLogin()).getId());
        return "greeting";
    }

    @GetMapping("/account")
    public @NotNull String account() {
        return "account";
    }
}
