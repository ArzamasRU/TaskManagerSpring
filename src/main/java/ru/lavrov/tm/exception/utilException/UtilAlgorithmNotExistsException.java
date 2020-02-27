package ru.lavrov.tm.exception.utilException;

public class UtilAlgorithmNotExistsException extends RuntimeException{
    private static final String message = "cryptographic algorithm is requested but is not available in the environment!";
    public UtilAlgorithmNotExistsException() {
        super(message);
    }
    public UtilAlgorithmNotExistsException(String message) {
        super(message);
    }
}
