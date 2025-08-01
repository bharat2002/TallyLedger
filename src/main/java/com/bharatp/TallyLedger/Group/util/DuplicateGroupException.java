package com.bharatp.TallyLedger.Group.util;

public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException(String field, String value)
    {
        super(String.format("Duplicate group found with %s: %s", field, value));
    }
}
