package ru.lavrov.tm.exception.command;

public class CommandIsInvalidException extends RuntimeException{
    private static final String message = "command is empty or null!";
    public CommandIsInvalidException() {
        super(message);
    }
    public CommandIsInvalidException(String message) {
        super(message);
    }
}
