package ru.lavrov.tm.restController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.dto.UserDTO;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.db.RequestIsFailedException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/user",  produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/findUserByLogin/{login}")
    public UserDTO findUserByLogin(@PathVariable("login") @Nullable final String login) {
        return User.getUserDTO(userService.findUserByLogin(login));
    }

    @GetMapping("/findOne/{id}")
    public UserDTO findOne(@PathVariable("id") @Nullable final String id) {
        return User.getUserDTO(userService.findOne(id));
    }

    @PostMapping(value = "/persist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void persist(@RequestBody @Nullable final UserDTO userDTO) {
        userService.persist(User.getUserFromDTO(userDTO));
    }

    @PostMapping(value = "/merge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void merge(@RequestBody @Nullable final UserDTO userDTO) {
        userService.merge(User.getUserFromDTO(userDTO));
    }

    @GetMapping("/removeUser/{id}")
    public void removeUser(@PathVariable("id") @Nullable final String id) {
        userService.removeUser(id);
    }

    @GetMapping("/updateLogin/{id}/{newLogin}")
    public void updateLogin(@NotNull final String id, @NotNull final String newLogin) {
            userService.updateLogin(userId, newLogin);
    }

//    @GetMapping(value = "/findUserByLogin2", produces = "application/json")
//    public Date findUserByLogin2() {
//        return new Date();
//    }
//
//    @GetMapping(value = "/findUserByLogin3", produces = MediaType.APPLICATION_JSON_VALUE)
//    public A findUserByLogin3() {
//        return new A();
//    }

//    objectMapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(environment.getProperty("projects_file_path") + ".json"), projectList);

//    public static class A{
//        public String str = "asd";
//    }

    //    @GetMapping("/findAll")
//    @ResponseBody
//    public Collection<UserDTO> findAll() {
//        return userService.getListUserDTO(userService.findAll());
//    }
}
