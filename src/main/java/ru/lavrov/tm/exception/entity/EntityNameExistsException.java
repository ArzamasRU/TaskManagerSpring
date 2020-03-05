package ru.lavrov.tm.exception.entity;

public final class EntityNameExistsException extends RuntimeException{
    private static final String message = "entity name already exists!";
    public EntityNameExistsException() {
        super(message);
    }
    public EntityNameExistsException(final String message) {
        super(message);
    }
}
