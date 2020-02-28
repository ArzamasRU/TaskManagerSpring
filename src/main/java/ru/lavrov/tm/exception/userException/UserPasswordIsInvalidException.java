package ru.lavrov.tm.exception.userException;

public class UserPasswordIsInvalidException extends RuntimeException{
    private static final String message = "login is empty or null!";
    public UserPasswordIsInvalidException() {
        super(message);
    }
    public UserPasswordIsInvalidException(String message) {
        super(message);
    }
}