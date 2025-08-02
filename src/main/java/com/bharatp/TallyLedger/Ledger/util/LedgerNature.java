package com.bharatp.TallyLedger.Ledger.util;

public enum LedgerNature {

    CAPITAL(BalanceType.CREDIT),
    RESERVE(BalanceType.CREDIT),
    LOAN(BalanceType.CREDIT),
    LIABILITY(BalanceType.CREDIT),
    CURRENT_LIABILITY(BalanceType.CREDIT),
    EQUITY(BalanceType.CREDIT),

    ASSET(BalanceType.DEBIT),
    FIXED_ASSET(BalanceType.DEBIT),
    CURRENT_ASSET(BalanceType.DEBIT),

    INCOME(BalanceType.CREDIT),
    INDIRECT_INCOME(BalanceType.CREDIT),

    EXPENSE(BalanceType.DEBIT),
    INDIRECT_EXPENSE(BalanceType.DEBIT),

    BANK(BalanceType.DEBIT),
    CASH(BalanceType.DEBIT),

    PROFIT_AND_LOSS(BalanceType.CREDIT),
    SUSPENSE(null),
    OTHER(null);

    private final BalanceType defaultBalanceType;

    LedgerNature(BalanceType defaultBalanceType) {
        this.defaultBalanceType = defaultBalanceType;
    }

    public BalanceType getDefaultBalanceType() {
        return defaultBalanceType;
    }

    public enum BalanceType {
        DEBIT, CREDIT
    }
}
