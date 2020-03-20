package ru.lavrov.tm.exception.project;

public final class ProjectExistsException extends RuntimeException {
    private static final String message = "project already exists!";

    public ProjectExistsException() {
        super(message);
    }

    public ProjectExistsException(final String message) {
        super(message);
    }
}
