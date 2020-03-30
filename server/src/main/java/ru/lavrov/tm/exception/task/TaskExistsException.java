package ru.lavrov.tm.exception.task;

public final class TaskExistsException extends RuntimeException {
    private static final String message = "task already exists!";

    public TaskExistsException() {
        super(message);
    }

    public TaskExistsException(final String message) {
        super(message);
    }
}
