package dev.murasakin.shinpo.core.application.exception;

public class NotFoundApplicationException extends RuntimeException {
    public NotFoundApplicationException(String message) {
        super(message);
    }
}
