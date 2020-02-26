package ru.lavrov.tm.exception.projectException;

public class ProjectExistsException extends RuntimeException{
    private static final String message = "project already exists!";
    public ProjectExistsException() {
        super(message);
    }
    public ProjectExistsException(String message) {
        super(message);
    }
}
