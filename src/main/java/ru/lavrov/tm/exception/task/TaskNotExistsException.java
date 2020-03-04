package ru.lavrov.tm.exception.task;

public final class TaskNotExistsException extends RuntimeException{
    private static final String message = "task does not exist";
    public TaskNotExistsException() {
        super(message);
    }
    public TaskNotExistsException(final String message) {
        super(message);
    }
}
