package ru.lavrov.tm.exception.userException;

public class UserLoginOrPasswordIsIncorrectException extends RuntimeException{
    private static final String message = "login or password is incorrect!";
    public UserLoginOrPasswordIsIncorrectException() {
        super(message);
    }
    public UserLoginOrPasswordIsIncorrectException(String message) {
        super(message);
    }
}
