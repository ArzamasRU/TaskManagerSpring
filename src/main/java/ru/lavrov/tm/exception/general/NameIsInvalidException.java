package ru.lavrov.tm.exception.general;

public final class NameIsInvalidException extends RuntimeException{
    private static final String message = "name is empty or null!";
    public NameIsInvalidException() {
        super(message);
    }
    public NameIsInvalidException(final String message) {
        super(message);
    }
}
