package ru.lavrov.tm.exception.task;

public class TaskNameIsInvalidException extends RuntimeException{
    private static final String message = "task name is empty or null!";
    public TaskNameIsInvalidException() {
        super(message);
    }
    public TaskNameIsInvalidException(String message) {
        super(message);
    }
}
