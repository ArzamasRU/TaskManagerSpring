package ru.lavrov.tm.exception.user;

public final class UserDoNotHavePermissionException extends RuntimeException {
    private static final String message = "user does not have enough rights!";

    public UserDoNotHavePermissionException() {
        super(message);
    }

    public UserDoNotHavePermissionException(final String message) {
        super(message);
    }
}
