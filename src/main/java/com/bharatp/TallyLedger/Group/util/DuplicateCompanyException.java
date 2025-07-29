package com.bharatp.TallyLedger.Group.util;

public class DuplicateCompanyException extends RuntimeException {
    public DuplicateCompanyException(String field, String value)
    {
        super(String.format("Duplicate company found with %s: %s", field, value));
    }
}
