package com.luxoft.exception;

import java.time.LocalDate;

public class RiservaNotFoundException extends RuntimeException {
    public RiservaNotFoundException(LocalDate date) {
        super("Riserva not found for date: " + date);
    }
}
