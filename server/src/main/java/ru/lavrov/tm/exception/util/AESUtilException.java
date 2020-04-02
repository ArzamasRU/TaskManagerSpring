package ru.lavrov.tm.exception.util;

public final class AESUtilException extends RuntimeException {
    private static final String message = "AES encryption error!";

    public AESUtilException() {
        super(message);
    }

    public AESUtilException(final String message) {
        super(message);
    }
}
