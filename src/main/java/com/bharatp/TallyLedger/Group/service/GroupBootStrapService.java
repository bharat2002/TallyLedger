package com.bharatp.TallyLedger.Group.service;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.repository.CompanyRepository;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.repository.GroupRepository;
import com.bharatp.TallyLedger.Group.util.GroupNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class GroupBootstrapService {

    private final GroupRepository groupRepo;
    private final CompanyRepository companyRepo;

    @Autowired
    public GroupBootstrapService(GroupRepository groupRepo, CompanyRepository companyRepo) {
        this.groupRepo = groupRepo;
        this.companyRepo = companyRepo;
    }


    @Transactional
    public void initDefaultsForCompany(Long companyId) {
        CompanyEntity company = companyRepo.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found: " + companyId));

        if (groupRepo.existsByCompanyAndReservedTrue(company)) {
            return;
        }

        List<GroupEntity> toSave = new ArrayList<>();

        // Balance Sheet Top‑Level
        GroupEntity nonCurrentAssets = buildTopGroup("Non Current Assets", company, GroupNature.NON_CURRENT_ASSETS);
        GroupEntity currentAssets    = buildTopGroup("Current Assets", company, GroupNature.CURRENT_ASSETS);
        GroupEntity equity           = buildTopGroup("Equity", company, GroupNature.EQUITY);
        GroupEntity nonCurrentLiab   = buildTopGroup("Non Current Liabilities", company, GroupNature.NON_CURRENT_LIABILITIES);
        GroupEntity currentLiab      = buildTopGroup("Current Liabilities", company, GroupNature.CURRENT_LIABILITIES);

        // Profit & Loss Top‑Level
        GroupEntity income           = buildTopGroup("Income", company, GroupNature.INCOME);
        GroupEntity expense          = buildTopGroup("Expenses", company, GroupNature.EXPENSE);

        // Collect top‑levels
        toSave.add(nonCurrentAssets);
        toSave.add(currentAssets);
        toSave.add(equity);
        toSave.add(nonCurrentLiab);
        toSave.add(currentLiab);
        toSave.add(income);
        toSave.add(expense);

        // Sub‑groups under Current Assets
        toSave.add(buildSubGroup("Cash-in-Hand",    currentAssets, GroupNature.CURRENT_ASSETS));
        toSave.add(buildSubGroup("Bank Accounts",   currentAssets, GroupNature.CURRENT_ASSETS));
        toSave.add(buildSubGroup("Sundry Debtors",  currentAssets, GroupNature.CURRENT_ASSETS));

        // Sub‑groups under Current Liabilities
        toSave.add(buildSubGroup("Sundry Creditors", currentLiab, GroupNature.CURRENT_LIABILITIES));
        toSave.add(buildSubGroup("Provisions",       currentLiab, GroupNature.CURRENT_LIABILITIES));

        // Sub‑groups under Equity
        toSave.add(buildSubGroup("Capital Account", equity, GroupNature.EQUITY));
        toSave.add(buildSubGroup("Reserves & Surplus", equity, GroupNature.EQUITY));

        // Sub‑groups under Income
        toSave.add(buildSubGroup("Sales Accounts",      income, GroupNature.INCOME));
        toSave.add(buildSubGroup("Other Income",        income, GroupNature.INCOME));

        // Sub‑groups under Expenses
        toSave.add(buildSubGroup("Direct Expenses",     expense, GroupNature.EXPENSE));
        toSave.add(buildSubGroup("Indirect Expenses",   expense, GroupNature.EXPENSE));

        // Sub‑groups under Non Current Assets
        toSave.add(buildSubGroup("Fixed Assets",        nonCurrentAssets, GroupNature.NON_CURRENT_ASSETS));
        toSave.add(buildSubGroup("Investments",         nonCurrentAssets, GroupNature.NON_CURRENT_ASSETS));

        // Sub‑groups under Non Current Liabilities
        toSave.add(buildSubGroup("Long Term Loans",     nonCurrentLiab, GroupNature.NON_CURRENT_LIABILITIES));

        // Save all in one batch
        groupRepo.saveAll(toSave);
    }

    /** Helper to build a top‑level (parent) group */
    private GroupEntity buildTopGroup(String name, CompanyEntity company, GroupNature nature) {
        return GroupEntity.builder()
                .company(company)
                .name(name)
                .nature(nature)
                .affectsGrossProfit(nature == GroupNature.INCOME)
                .reserved(true)
                .build();
    }

    /** Helper to build a sub‑group under a parent */
    private GroupEntity buildSubGroup(String name, GroupEntity parent, GroupNature nature) {
        return GroupEntity.builder()
                .company(parent.getCompany())
                .parent(parent)
                .name(name)
                .nature(nature)
                .affectsGrossProfit(nature == GroupNature.EXPENSE || nature == GroupNature.INCOME)
                .reserved(true)
                .build();
    }
}
