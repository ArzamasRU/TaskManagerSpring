package ru.lavrov.tm.exception.entity;

public final class EntityCanNotBeComparedException extends RuntimeException {
    private static final String message = "entities cannot be compared!";

    public EntityCanNotBeComparedException() {
        super(message);
    }

    public EntityCanNotBeComparedException(final String message) {
        super(message);
    }
}
