package dev.murasakin.shinpo.application.exception;

public class NotFoundApplicationException extends RuntimeException {
    public NotFoundApplicationException(String message) {
        super(message);
    }
}
