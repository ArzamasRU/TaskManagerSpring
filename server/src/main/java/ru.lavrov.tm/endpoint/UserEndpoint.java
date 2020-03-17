package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TestEndpoint")
public final class UserEndpoint {

    @NotNull public static final String URL = "http://localhost:8090/TestEndpoint?wsdl";


}
