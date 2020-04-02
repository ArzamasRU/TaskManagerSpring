package ru.lavrov.tm.exception.common;

public final class DescriptionIsInvalidException extends RuntimeException {
    private static final String message = "description is empty or null!";

    public DescriptionIsInvalidException() {
        super(message);
    }

    public DescriptionIsInvalidException(final String message) {
        super(message);
    }
}
