package com.shipmonk.testingday.provider;

/**
 * Thrown when you can not get exchange rates because of any reason.
 */
public class CanNotGetExchangeRatesException extends RuntimeException {

    public CanNotGetExchangeRatesException(Throwable cause) {
        super(cause);
    }

    public CanNotGetExchangeRatesException(String message) {
        super(message);
    }

    public CanNotGetExchangeRatesException(String message, Throwable cause) {
        super(message, cause);
    }
}
