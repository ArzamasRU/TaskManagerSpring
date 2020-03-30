package ru.lavrov.tm.exception.entity;

public final class EntityExistsException extends RuntimeException {
    private static final String message = "entity already exists!";

    public EntityExistsException() {
        super(message);
    }

    public EntityExistsException(final String message) {
        super(message);
    }
}
