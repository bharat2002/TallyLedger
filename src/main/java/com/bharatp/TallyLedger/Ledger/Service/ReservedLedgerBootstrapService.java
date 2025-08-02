package com.bharatp.TallyLedger.Ledger.Service;

import com.bharatp.TallyLedger.Common.Exeption.NotFoundException;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.repository.CompanyRepository;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.repository.GroupRepository;
import com.bharatp.TallyLedger.Ledger.Entity.LedgerEntity;
import com.bharatp.TallyLedger.Ledger.Repository.LedgerRepository;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ReservedLedgerBootstrapService {

    private final CompanyRepository companyRepo;
    private final GroupRepository groupRepo;
    private final LedgerRepository ledgerRepo;

    @Autowired
    public ReservedLedgerBootstrapService(CompanyRepository companyRepo, GroupRepository groupRepo, LedgerRepository ledgerRepo) {
        this.companyRepo = companyRepo;
        this.groupRepo = groupRepo;
        this.ledgerRepo = ledgerRepo;
    }

    @Transactional
    public void initReservedLedgers(Long companyId) {
        CompanyEntity company = companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));

        // Skip if already bootstrapped
        if (ledgerRepo.existsByReservedTrueAndCompany_Id(companyId)) {
            return;
        }

//        GroupEntity grpEntity = groupRepo.findByNameAndCompany_Id(name,companyId);
        Function<String, GroupEntity> grp = name ->
                groupRepo.findByNameAndCompany_Id(name, companyId)
                        .orElseThrow(() -> new NotFoundException("Group", name));

        List<LedgerEntity> toSave = new ArrayList<>();

        // 1. Cash-in-hand
        toSave.add(LedgerEntity.builder()
                .setName("Cash")
                .setNature(LedgerNature.CASH)
                .setCompany(company)
                .setGroup(grp.apply("Cash-in-Hand"))
                .setReserved(true)
                .build());

        // 2. Bank Accounts (generic)
        toSave.add(LedgerEntity.builder()
                .setName("Bank Accounts")
                .setNature(LedgerNature.BANK)
                .setCompany(company)
                .setGroup(grp.apply("Bank Accounts"))
                .setReserved(true)
                .build());

        // 3. Suspense A/c
        toSave.add(LedgerEntity.builder()
                .setName("Suspense A/c")
                .setNature(LedgerNature.SUSPENSE)
                .setCompany(company)
                .setGroup(grp.apply("Suspense Account"))
                .setReserved(true)
                .build());

        // 4. Profit & Loss A/c
        toSave.add(LedgerEntity.builder()
                .setName("Profit & Loss A/c")
                .setNature(LedgerNature.PROFIT_AND_LOSS)
                .setCompany(company)
                .setGroup(grp.apply("Reserves & Surplus"))
                .setReserved(true)
                .build());

        // 5. Round Off
        toSave.add(LedgerEntity.builder()
                .setName("Round Off")
                .setNature(LedgerNature.INDIRECT_EXPENSE)
                .setCompany(company)
                .setGroup(grp.apply("Indirect Expenses"))
                .setReserved(true)
                .build());

        // 6. Duties & Taxes — GST Payable / Receivable
        toSave.add(LedgerEntity.builder()
                .setName("GST Payable")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Input CGST")
                .setNature(LedgerNature.CURRENT_ASSET)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Input SGST")
                .setNature(LedgerNature.CURRENT_ASSET)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Input IGST")
                .setNature(LedgerNature.CURRENT_ASSET)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Output CGST")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Output SGST")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("GST Output IGST")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());

        // 7. TDS & TCS
        toSave.add(LedgerEntity.builder()
                .setName("TDS Payable")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("TCS Receivable")
                .setNature(LedgerNature.CURRENT_ASSET)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());

        // 8. Capital & Drawings
        toSave.add(LedgerEntity.builder()
                .setName("Capital Account")
                .setNature(LedgerNature.EQUITY)
                .setCompany(company)
                .setGroup(grp.apply("Capital Account"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("Drawings")
                .setNature(LedgerNature.EQUITY)
                .setCompany(company)
                .setGroup(grp.apply("Capital Account"))
                .setReserved(true)
                .build());

        // 9. Other Statutory / System Ledgers
        toSave.add(LedgerEntity.builder()
                .setName("Input Service Tax")
                .setNature(LedgerNature.CURRENT_ASSET)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());
        toSave.add(LedgerEntity.builder()
                .setName("Output Service Tax")
                .setNature(LedgerNature.LIABILITY)
                .setCompany(company)
                .setGroup(grp.apply("Duties & Taxes"))
                .setReserved(true)
                .build());


        ledgerRepo.saveAll(toSave);
    }
}
