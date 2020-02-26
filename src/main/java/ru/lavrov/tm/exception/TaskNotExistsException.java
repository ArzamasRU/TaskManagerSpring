package ru.lavrov.tm.exception;

public class TaskNotExistsException extends RuntimeException{
    private static final String message = "task does not exist";
    public TaskNotExistsException() {
        super(message);
    }
    public TaskNotExistsException(String message) {
        super(message);
    }
}
