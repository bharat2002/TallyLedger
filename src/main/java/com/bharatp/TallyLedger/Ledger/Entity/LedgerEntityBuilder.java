package com.bharatp.TallyLedger.Ledger.Entity;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;

import java.math.BigDecimal;

public class LedgerEntityBuilder {
    private LedgerNature nature;
    private String name;
    private String alias;
    private CompanyEntity company;
    private GroupEntity group;
    private BigDecimal openingBalance;
    private boolean reserved;
    private String pan;
    private String gstin;
    private String contactPerson;
    private String phone;
    private String email;
    private String description;
    public LedgerEntity build()
    {
        return new LedgerEntity(
                nature,
                name,
                alias,
                company,
                group,
                openingBalance,
                reserved,
                pan,
                gstin,
                contactPerson,
                phone,
                email,
                description
        );
    }
    public LedgerEntityBuilder setNature(LedgerNature nature) {
        this.nature = nature;
        return this;
    }

    public LedgerEntityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LedgerEntityBuilder setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public LedgerEntityBuilder setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }

    public LedgerEntityBuilder setGroup(GroupEntity group) {
        this.group = group;
        return this;
    }

    public LedgerEntityBuilder setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
        return this;
    }

    public LedgerEntityBuilder setReserved(boolean reserved) {
        this.reserved = reserved;
        return this;
    }

    public LedgerEntityBuilder setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public LedgerEntityBuilder setGstin(String gstin) {
        this.gstin = gstin;
        return this;
    }

    public LedgerEntityBuilder setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public LedgerEntityBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LedgerEntityBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public LedgerEntityBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public LedgerEntity createLedgerEntity() {
        return new LedgerEntity(nature, name, alias, company, group, openingBalance, reserved, pan, gstin, contactPerson, phone, email, description);
    }
}