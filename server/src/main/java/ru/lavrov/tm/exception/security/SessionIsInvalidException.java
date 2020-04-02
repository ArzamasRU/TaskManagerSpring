package ru.lavrov.tm.exception.security;

public final class SessionIsInvalidException extends RuntimeException {
    private static final String message = "Session is invalid!";

    public SessionIsInvalidException() {
        super(message);
    }

    public SessionIsInvalidException(final String message) {
        super(message);
    }
}
