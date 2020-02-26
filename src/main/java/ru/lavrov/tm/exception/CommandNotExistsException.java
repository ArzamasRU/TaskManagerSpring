package ru.lavrov.tm.exception;

public class CommandNotExistsException extends RuntimeException{
    private static final String message = "command does not exist";
    public CommandNotExistsException() {
        super(message);
    }
    public CommandNotExistsException(String message) {
        super(message);
    }
}
