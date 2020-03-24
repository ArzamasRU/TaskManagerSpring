package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.user.UserDoNotHavePermissionException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.UserEndpoint")
public final class UserEndpoint2 extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/UserEndpoint?wsdl";
}
