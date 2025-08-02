package com.bharatp.TallyLedger.Ledger.util;

public class DuplicateLedgerException extends RuntimeException {
    public DuplicateLedgerException(String field, String value)
    {
        super(String.format("Duplicate ledger found with %s: %s", field, value));
    }
}
