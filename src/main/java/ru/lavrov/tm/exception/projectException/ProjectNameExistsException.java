package ru.lavrov.tm.exception.projectException;

public class ProjectNameExistsException extends RuntimeException{
    private static final String message = "project name already exists!";
    public ProjectNameExistsException() {
        super(message);
    }
    public ProjectNameExistsException(String message) {
        super(message);
    }
}
