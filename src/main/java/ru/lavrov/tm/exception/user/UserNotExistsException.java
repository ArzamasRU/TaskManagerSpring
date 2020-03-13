package ru.lavrov.tm.exception.user;

public final class UserNotExistsException extends RuntimeException {
    private static final String message = "user does not exists!";

    public UserNotExistsException() {
        super(message);
    }

    public UserNotExistsException(final String message) {
        super(message);
    }
}
