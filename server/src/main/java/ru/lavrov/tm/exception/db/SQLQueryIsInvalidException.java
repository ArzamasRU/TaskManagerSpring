package ru.lavrov.tm.exception.db;

public final class SQLQueryIsInvalidException extends RuntimeException {
    private static final String message = "SQL query is invalid!";

    public SQLQueryIsInvalidException() {
        super(message);
    }

    public SQLQueryIsInvalidException(final String message) {
        super(message);
    }
}
