package ru.lavrov.tm.exception.user;

public final class UserLoginExistsException extends RuntimeException {
    private static final String message = "login already exists!";

    public UserLoginExistsException() {
        super(message);
    }

    public UserLoginExistsException(final String message) {
        super(message);
    }
}
