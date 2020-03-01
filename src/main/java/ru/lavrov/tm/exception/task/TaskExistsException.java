package ru.lavrov.tm.exception.task;

public class TaskExistsException extends RuntimeException{
    private static final String message = "task already exists!";
    public TaskExistsException() {
        super(message);
    }
    public TaskExistsException(String message) {
        super(message);
    }
}
