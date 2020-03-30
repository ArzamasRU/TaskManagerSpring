package ru.lavrov.tm.exception.entity;

public final class EntityNotExistsException extends RuntimeException {
    private static final String message = "entity does not exist";

    public EntityNotExistsException() {
        super(message);
    }

    public EntityNotExistsException(final String message) {
        super(message);
    }
}
