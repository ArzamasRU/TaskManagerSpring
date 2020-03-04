package ru.lavrov.tm.exception.user;

public final class UserLoginOrPasswordIsIncorrectException extends RuntimeException{
    private static final String message = "login or password is incorrect!";
    public UserLoginOrPasswordIsIncorrectException() {
        super(message);
    }
    public UserLoginOrPasswordIsIncorrectException(final String message) {
        super(message);
    }
}
