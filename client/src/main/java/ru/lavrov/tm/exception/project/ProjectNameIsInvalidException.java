package ru.lavrov.tm.exception.project;

public final class ProjectNameIsInvalidException extends RuntimeException {
    private static final String message = "project name is empty or null!";

    public ProjectNameIsInvalidException() {
        super(message);
    }

    public ProjectNameIsInvalidException(final String message) {
        super(message);
    }
}
