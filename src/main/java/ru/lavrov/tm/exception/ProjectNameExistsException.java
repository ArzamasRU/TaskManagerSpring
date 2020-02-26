package ru.lavrov.tm.exception;

public class ProjectNameExistsException extends RuntimeException{
    private static final String message = "project name already exists!";
    public ProjectNameExistsException() {
        super(message);
    }
    public ProjectNameExistsException(String message) {
        super(message);
    }
}
