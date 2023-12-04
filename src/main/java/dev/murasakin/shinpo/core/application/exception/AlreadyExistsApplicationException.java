package dev.murasakin.shinpo.core.application.exception;

public class AlreadyExistsApplicationException extends RuntimeException {
    public AlreadyExistsApplicationException(String message) {
        super(message);
    }
}
