package ru.lavrov.tm.exception.db;

public final class AccessDBException extends RuntimeException {
    private static final String message = "Access the database error!";

    public AccessDBException() {
        super(message);
    }

    public AccessDBException(final String message) {
        super(message);
    }
}
