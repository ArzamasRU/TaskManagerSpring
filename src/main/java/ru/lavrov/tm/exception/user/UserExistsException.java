package ru.lavrov.tm.exception.user;

public final class UserExistsException extends RuntimeException{
    private static final String message = "user already exists!";
    public UserExistsException() {
        super(message);
    }
    public UserExistsException(final String message) {
        super(message);
    }
}
