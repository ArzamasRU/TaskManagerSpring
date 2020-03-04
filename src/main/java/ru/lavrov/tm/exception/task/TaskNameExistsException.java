package ru.lavrov.tm.exception.task;

public final class TaskNameExistsException extends RuntimeException{
    private static final String message = "task name already exists!";
    public TaskNameExistsException() {
        super(message);
    }
    public TaskNameExistsException(final String message) {
        super(message);
    }
}
