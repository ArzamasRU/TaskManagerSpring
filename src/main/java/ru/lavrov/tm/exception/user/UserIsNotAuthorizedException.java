package ru.lavrov.tm.exception.user;

public class UserIsNotAuthorizedException extends RuntimeException{
    private static final String message = "user is not authorized!";
    public UserIsNotAuthorizedException() {
        super(message);
    }
    public UserIsNotAuthorizedException(String message) {
        super(message);
    }
}
