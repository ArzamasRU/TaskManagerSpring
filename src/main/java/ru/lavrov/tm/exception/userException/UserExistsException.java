package ru.lavrov.tm.exception.userException;

public class UserExistsException extends RuntimeException{
    private static final String message = "user already exists!";
    public UserExistsException() {
        super(message);
    }
    public UserExistsException(String message) {
        super(message);
    }
}
