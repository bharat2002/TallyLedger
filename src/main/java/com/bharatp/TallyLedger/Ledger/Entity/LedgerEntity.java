package com.bharatp.TallyLedger.Ledger.Entity;

import com.bharatp.TallyLedger.Common.BaseEntity;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(
        name = "ledgers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"})
)
public class LedgerEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LedgerNature nature;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 150)
    private String alias;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @Column(precision = 19, scale = 2)
    private BigDecimal openingBalance;

    @Column(nullable = false)
    private boolean reserved = false;

    @Column(length = 50)
    private String pan;

    @Column(length = 15)
    private String gstin;

    @Column(length = 100)
    private String contactPerson;

    @Column(length = 15)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 500)
    private String description;

    public LedgerEntity() {
    }

    public static LedgerEntityBuilder builder()
    {
        return new LedgerEntityBuilder();
    }

    public LedgerEntity(LedgerNature nature, String name, String alias, CompanyEntity company, GroupEntity group, BigDecimal openingBalance, boolean reserved, String pan, String gstin, String contactPerson, String phone, String email, String description) {
        this.nature = nature;
        this.name = name;
        this.alias = alias;
        this.company = company;
        this.group = group;
        this.openingBalance = openingBalance;
        this.reserved = reserved;
        this.pan = pan;
        this.gstin = gstin;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    public LedgerNature getNature() {
        return nature;
    }

    public void setNature(LedgerNature nature) {
        this.nature = nature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
