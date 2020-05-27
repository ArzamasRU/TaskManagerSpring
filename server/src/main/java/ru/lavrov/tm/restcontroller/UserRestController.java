package ru.lavrov.tm.restcontroller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.dto.UserDTO;
import ru.lavrov.tm.entity.User;

import static ru.lavrov.tm.util.HashUtil.md5Hard;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/findUserByLogin/{login}")
    public UserDTO findUserByLogin(@PathVariable("login") @Nullable final String login) {
        return User.getUserDTO(userService.findUserByLogin(login));
    }

    @GetMapping("/findOne/{id}")
    public UserDTO findOne(@PathVariable("id") @Nullable final String id) {
        return User.getUserDTO(userService.findOne(id));
    }

    @PostMapping(value = "/persist")
    public void persist(@RequestBody @Nullable final UserDTO userDTO) {
        userService.persist(User.getUserFromDTO(userDTO));
    }

    @PostMapping(value = "/merge")
    public void merge(@RequestBody @Nullable final UserDTO userDTO) {
        userService.merge(User.getUserFromDTO(userDTO));
    }

    @GetMapping("/removeUser/{id}")
    public void removeUser(@PathVariable("id") @Nullable final String id) {
        userService.removeUser(id);
    }

//    /rest/user/createByLogin?login=user3&password=user3&role=USER
    @GetMapping(value = "/createByLogin")
    public void createByLogin(@RequestParam("login") @Nullable final String login,
                              @RequestParam("password") @Nullable final String password,
                              @RequestParam("role") @Nullable final String role
    ) {
        @Nullable final String hashedPassword = md5Hard(password);
        userService.createByLogin(login, hashedPassword, role);
    }
}
