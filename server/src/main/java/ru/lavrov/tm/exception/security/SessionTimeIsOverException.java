package ru.lavrov.tm.exception.security;

public final class SessionTimeIsOverException extends RuntimeException {
    private static final String message = "Session time is over!";

    public SessionTimeIsOverException() {
        super(message);
    }

    public SessionTimeIsOverException(final String message) {
        super(message);
    }
}
