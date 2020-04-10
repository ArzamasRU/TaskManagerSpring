package ru.lavrov.tm.exception.util;

public final class SerializationException extends RuntimeException {
    private static final String message = "Serialization algorithm error!";

    public SerializationException() {
        super(message);
    }

    public SerializationException(final String message) {
        super(message);
    }
}
