package ru.lavrov.tm.exception.project;

public final class ProjectNameExistsException extends RuntimeException{
    private static final String message = "project name already exists!";
    public ProjectNameExistsException() {
        super(message);
    }
    public ProjectNameExistsException(final String message) {
        super(message);
    }
}
