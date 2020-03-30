package ru.lavrov.tm.exception.property;

public final class PropertyLoadingException extends RuntimeException {
    private static final String message = "Application properties loading error!";

    public PropertyLoadingException() {
        super(message);
    }

    public PropertyLoadingException(final String message) {
        super(message);
    }
}
