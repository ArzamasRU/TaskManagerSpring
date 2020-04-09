package ru.lavrov.tm.exception.db;

public final class RequestIsFailedException extends RuntimeException {
    private static final String message = "Request to db is failed!";

    public RequestIsFailedException() {
        super(message);
    }

    public RequestIsFailedException(final String message) {
        super(message);
    }
}
