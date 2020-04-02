package ru.lavrov.tm.exception.security;

public final class TokenIsInvalidException extends RuntimeException {
    private static final String message = "Token is invalid!";

    public TokenIsInvalidException() {
        super(message);
    }

    public TokenIsInvalidException(final String message) {
        super(message);
    }
}
