package ru.lavrov.tm.exception.command;

public final class CommandDescNotExistsException extends RuntimeException{
    private static final String message = "command description does not exist";
    public CommandDescNotExistsException() {
        super(message);
    }
    public CommandDescNotExistsException(final String message) {
        super(message);
    }
}
