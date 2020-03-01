package ru.lavrov.tm.exception.user;

public class UserLoginExistsException extends RuntimeException{
    private static final String message = "login already exists!";
    public UserLoginExistsException() {
        super(message);
    }
    public UserLoginExistsException(String message) {
        super(message);
    }
}
