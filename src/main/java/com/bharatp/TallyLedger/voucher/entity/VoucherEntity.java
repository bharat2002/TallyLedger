package com.bharatp.TallyLedger.voucher.entity;

import com.bharatp.TallyLedger.Common.BaseEntity;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vouchers")
public class VoucherEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voucher_type_id", nullable = false)
    private VoucherTypeEntity voucherType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "voucher_number", nullable = false)
    private String voucherNumber;

    private String narration;


    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoucherEntryEntity> entries = new ArrayList<>();

    @Column(nullable = false)
    private boolean reserved = false;


    public void addEntry(VoucherEntryEntity entry) {
        entries.add(entry);
        entry.setVoucher(this);
    }

    public void removeEntry(VoucherEntryEntity entry) {
        entries.remove(entry);
        entry.setVoucher(null);
    }


    public VoucherEntity(VoucherTypeEntity voucherType, CompanyEntity company, LocalDate date, String voucherNumber, String narration, List<VoucherEntryEntity> entries, boolean reserved) {
        this.voucherType = voucherType;
        this.company = company;
        this.date = date;
        this.voucherNumber = voucherNumber;
        this.narration = narration;
        this.entries = entries;
        this.reserved = reserved;
    }

    public VoucherTypeEntity getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherTypeEntity voucherType) {
        this.voucherType = voucherType;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public List<VoucherEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(List<VoucherEntryEntity> entries) {
        this.entries = entries;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
