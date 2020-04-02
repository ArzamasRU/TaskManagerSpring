package ru.lavrov.tm.exception.security;

public final class SessionSignIsInvalidException extends RuntimeException {
    private static final String message = "Session sign is invalid!";

    public SessionSignIsInvalidException() {
        super(message);
    }

    public SessionSignIsInvalidException(final String message) {
        super(message);
    }
}
