package com.bharatp.TallyLedger.voucher.entity;

import com.bharatp.TallyLedger.Common.BaseEntity;
import com.bharatp.TallyLedger.Ledger.Entity.LedgerEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "voucher_entries")
public class VoucherEntryEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voucher_id", nullable = false)
    private VoucherEntity voucher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ledger_id", nullable = false)
    private LedgerEntity ledger;

    @Column(name = "debit_amount", precision = 18, scale = 2)
    private BigDecimal debitAmount;

    @Column(name = "credit_amount", precision = 18, scale = 2)
    private BigDecimal creditAmount;

    private String narration;

    public boolean isDebit() {
        return debitAmount != null && debitAmount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isCredit() {
        return creditAmount != null && creditAmount.compareTo(BigDecimal.ZERO) > 0;
    }

    public VoucherEntryEntity(VoucherEntity voucher, LedgerEntity ledger, BigDecimal debitAmount, BigDecimal creditAmount, String narration) {
        this.voucher = voucher;
        this.ledger = ledger;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.narration = narration;
    }

    public VoucherEntity getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }

    public LedgerEntity getLedger() {
        return ledger;
    }

    public void setLedger(LedgerEntity ledger) {
        this.ledger = ledger;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}
