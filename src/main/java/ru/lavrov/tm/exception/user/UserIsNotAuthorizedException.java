package ru.lavrov.tm.exception.user;

public final class UserIsNotAuthorizedException extends RuntimeException{
    private static final String message = "user is not authorized!";
    public UserIsNotAuthorizedException() {
        super(message);
    }
    public UserIsNotAuthorizedException(final String message) {
        super(message);
    }
}
