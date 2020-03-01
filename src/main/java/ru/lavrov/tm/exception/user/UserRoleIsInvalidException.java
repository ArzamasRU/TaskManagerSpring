package ru.lavrov.tm.exception.user;

public class UserRoleIsInvalidException extends RuntimeException{
    private static final String message = "role is empty or null!";
    public UserRoleIsInvalidException() {
        super(message);
    }
    public UserRoleIsInvalidException(String message) {
        super(message);
    }
}
