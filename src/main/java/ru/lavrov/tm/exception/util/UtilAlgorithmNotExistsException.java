package ru.lavrov.tm.exception.util;

public final class UtilAlgorithmNotExistsException extends RuntimeException{
    private static final String message = "cryptographic algorithm is requested but is not available in the environment!";
    public UtilAlgorithmNotExistsException() {
        super(message);
    }
    public UtilAlgorithmNotExistsException(final String message) {
        super(message);
    }
}
