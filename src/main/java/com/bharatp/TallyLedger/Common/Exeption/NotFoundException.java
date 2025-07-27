package com.bharatp.TallyLedger.Common.Exeption;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message,Object value) {
        super(message + " not found. Value: " + value);
    }

    public NotFoundException(String message)
    {
        super(message+" not found.");
    }
}
