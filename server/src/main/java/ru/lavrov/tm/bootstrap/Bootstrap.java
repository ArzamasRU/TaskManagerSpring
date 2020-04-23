package ru.lavrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.service.UserServiceImpl;

import javax.xml.ws.Endpoint;

import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Getter
@NoArgsConstructor
@Component
public final class Bootstrap {

    @Autowired
    @NotNull
    private IUserService userService;

    @Autowired
    @NotNull
    private UserEndpoint userEndpoint;

    @Autowired
    @NotNull
    private TokenEndpoint tokenEndpoint;

    @Autowired
    @NotNull
    private ProjectEndpoint projectEndpoint;

    @Autowired
    @NotNull
    private TaskEndpoint taskEndpoint;

    @Autowired
    @NotNull
    private GeneralCommandEndpoint generalCommandEndpoint;

    public void init() {
        initProperties();
        initUsers();
        initEndpoints();
        System.out.println("*** SERVER STARTED ***");
    }

    private void initEndpoints() {
        Endpoint.publish(UserEndpoint.URL, userEndpoint);
        Endpoint.publish(TokenEndpoint.URL, tokenEndpoint);
        Endpoint.publish(ProjectEndpoint.URL, projectEndpoint);
        Endpoint.publish(TaskEndpoint.URL, taskEndpoint);
        Endpoint.publish(GeneralCommandEndpoint.URL, generalCommandEndpoint);
    }

    private void initUsers() {
        if (userService.findUserByLogin("user") == null)
            userService.createByLogin("user", md5Hard("user"), Role.USER.name());
        if (userService.findUserByLogin("admin") == null)
            userService.createByLogin("admin", md5Hard("admin"), Role.ADMIN.name());
    }

    private void initProperties() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }
}

