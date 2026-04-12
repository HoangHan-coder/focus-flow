package dev.focusflow.exceptions;

public class DisabledException extends RuntimeException {
    public DisabledException() {
        super("Account suspended");
    }
}
