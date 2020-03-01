package ru.lavrov.tm.exception.user;

public class UserLoginNotExistsException extends RuntimeException{
    private static final String message = "login already exists!";
    public UserLoginNotExistsException() {
        super(message);
    }
    public UserLoginNotExistsException(String message) {
        super(message);
    }
}
