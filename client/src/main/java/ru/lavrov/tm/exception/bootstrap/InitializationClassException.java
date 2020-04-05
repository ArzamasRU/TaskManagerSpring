package ru.lavrov.tm.exception.bootstrap;

public final class InitializationClassException extends RuntimeException {
    private static final String message = "user does not have enough rights!";

    public InitializationClassException() {
        super(message);
    }

    public InitializationClassException(final String message) {
        super(message);
    }
}
