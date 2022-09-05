package com.workfusion.examples.aa_examples_bots.steam.automation.exceptions;

public class SteamApplicationException extends RuntimeException {

    public SteamApplicationException() {
    }

    public SteamApplicationException(String message) {
        super(message);
    }

    public SteamApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SteamApplicationException(Throwable cause) {
        super(cause);
    }

    public SteamApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
