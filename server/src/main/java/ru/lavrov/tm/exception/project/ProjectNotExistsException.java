package ru.lavrov.tm.exception.project;

public final class ProjectNotExistsException extends RuntimeException {
    private static final String message = "project does not exist";

    public ProjectNotExistsException() {
        super(message);
    }

    public ProjectNotExistsException(final String message) {
        super(message);
    }
}
