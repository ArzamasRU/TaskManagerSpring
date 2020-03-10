package ru.lavrov.tm.exception.user;

public final class UserLoginNotExistsException extends RuntimeException{
    private static final String message = "login not exists!";
    public UserLoginNotExistsException() {
        super(message);
    }
    public UserLoginNotExistsException(final String message) {
        super(message);
    }
}
