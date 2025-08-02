package com.bharatp.TallyLedger.Ledger.mapper;

import com.bharatp.TallyLedger.Group.util.GroupNature;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;

public class LedgerNatureMapper {

    public static LedgerNature fromGroupNature(GroupNature groupNature) {
        if (groupNature == null) {
            throw new IllegalArgumentException("GroupNature cannot be null");
        }

        return switch (groupNature) {
            case NON_CURRENT_ASSETS  -> LedgerNature.FIXED_ASSET;
            case CURRENT_ASSETS      -> LedgerNature.CURRENT_ASSET;
            case EQUITY              -> LedgerNature.EQUITY;
            case NON_CURRENT_LIABILITIES, CURRENT_LIABILITIES -> LedgerNature.LIABILITY;
            case INCOME              -> LedgerNature.INDIRECT_INCOME;
            case EXPENSE             -> LedgerNature.INDIRECT_EXPENSE;
        };
    }
}
