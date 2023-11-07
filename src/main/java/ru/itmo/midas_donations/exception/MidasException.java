package ru.itmo.midas_donations.exception;

public class MidasException extends RuntimeException {
    public MidasException() { }

    public MidasException(String message) {
        super(message);
    }

    public MidasException(String message, Exception exception) {
        super(message, exception);
    }
}
