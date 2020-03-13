package ru.lavrov.tm.exception.user;

public final class UserLoginIsInvalidException extends RuntimeException {
    private static final String message = "login is empty or null!";

    public UserLoginIsInvalidException() {
        super(message);
    }

    public UserLoginIsInvalidException(final String message) {
        super(message);
    }
}
