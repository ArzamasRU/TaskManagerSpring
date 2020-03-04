package ru.lavrov.tm.exception.user;

public final class UserPasswordIsInvalidException extends RuntimeException{
    private static final String message = "login is empty or null!";
    public UserPasswordIsInvalidException() {
        super(message);
    }
    public UserPasswordIsInvalidException(final String message) {
        super(message);
    }
}
