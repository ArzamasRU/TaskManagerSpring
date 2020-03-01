package ru.lavrov.tm.exception.command;

public class CommandDescNotExistsException extends RuntimeException{
    private static final String message = "command description does not exist";
    public CommandDescNotExistsException() {
        super(message);
    }
    public CommandDescNotExistsException(String message) {
        super(message);
    }
}
