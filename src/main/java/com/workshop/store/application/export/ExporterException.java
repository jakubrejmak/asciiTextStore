package com.workshop.store.application.export;

public class ExporterException extends Exception {

    public ExporterException(String message) {
        super(message);
    }

    public ExporterException(String message, Throwable inner) {
        super(message, inner);
    }
}
