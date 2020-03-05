package ru.lavrov.tm.exception.entity;

public final class EntityNameIsInvalidException extends RuntimeException{
    private static final String message = "entity name is empty or null!";
    public EntityNameIsInvalidException() {
        super(message);
    }
    public EntityNameIsInvalidException(final String message) {
        super(message);
    }
}
