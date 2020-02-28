package ru.lavrov.tm.exception.userException;

public class UserDoNotHavePermissionException extends RuntimeException{
    private static final String message = "user does not have enough rights!";
    public UserDoNotHavePermissionException() {
        super(message);
    }
    public UserDoNotHavePermissionException(String message) {
        super(message);
    }
}
