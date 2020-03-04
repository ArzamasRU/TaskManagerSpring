package ru.lavrov.tm.exception.command;

public final class CommandNotExistsException extends RuntimeException{
    private static final String message = "command does not exist";
    public CommandNotExistsException() {
        super(message);
    }
    public CommandNotExistsException(final String message) {
        super(message);
    }
}
