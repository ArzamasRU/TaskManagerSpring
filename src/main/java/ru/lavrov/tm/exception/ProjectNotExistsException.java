package ru.lavrov.tm.exception;

public class ProjectNotExistsException extends RuntimeException{
    private static final String message = "project does not exist";
    public ProjectNotExistsException() {
        super(message);
    }
    public ProjectNotExistsException(String message) {
        super(message);
    }
}