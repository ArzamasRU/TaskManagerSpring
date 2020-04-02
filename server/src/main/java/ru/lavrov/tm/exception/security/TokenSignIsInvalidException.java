package ru.lavrov.tm.exception.security;

public final class TokenSignIsInvalidException extends RuntimeException {
    private static final String message = "Token sign is invalid!";

    public TokenSignIsInvalidException() {
        super(message);
    }

    public TokenSignIsInvalidException(final String message) {
        super(message);
    }
}
