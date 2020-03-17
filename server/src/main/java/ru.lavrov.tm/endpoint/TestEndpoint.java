package ru.lavrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.lavrov.tm.endpoint.TestEndpoint")
public final class TestEndpoint {

    @NotNull public static final String URL = "http://localhost:8090/TestEndpoint?wsdl";

//    @NotNull
//    public String getUrl() {
//        return url;
//    }

    @WebMethod
    public String hello() {
        return "Hello!";
    }

    @WebMethod
    public String helloMe(String str) {
        return "Hello " + str + "!";
    }

    @WebMethod
    public int calculate(int i, int i2) {
        return i + i2;
    }
}
