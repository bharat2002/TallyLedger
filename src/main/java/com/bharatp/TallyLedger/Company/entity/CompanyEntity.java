package com.bharatp.TallyLedger.Company.entity;
import com.bharatp.TallyLedger.Company.util.GSTRegistrationType;
import com.bharatp.TallyLedger.Company.util.TaxExemptionType;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "companies", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"gstin"})
})
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String alias;
    private String mailingName;
    @Embedded
    private AddressEntity address;
    private String telephone;

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    private String mobile;
    private String email;
    private String website;

    private String panNumber;
    private String cinNumber;

    @Enumerated(EnumType.STRING)
    private GSTRegistrationType gstRegistrationType;  // REGULAR, COMPOSITION, SEZ, UNREGISTERED
    private String gstin;

    private String iecCode;

    @Enumerated(EnumType.STRING)
    private TaxExemptionType taxExemptionType;        // NONE, PARTIAL, FULL
    private String taxExemptionDetails;

    private LocalDate financialYearStart;
    private LocalDate financialYearEnd;
    private LocalDate booksBeginFrom;

    private String baseCurrency;
    private Boolean multiCurrencyEnabled = false;

    private Boolean enabled = true;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    void onCreate()
    {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate()
    {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMailingName() {
        return mailingName;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getCinNumber() {
        return cinNumber;
    }

    public void setCinNumber(String cinNumber) {
        this.cinNumber = cinNumber;
    }

    public GSTRegistrationType getGstRegistrationType() {
        return gstRegistrationType;
    }

    public void setGstRegistrationType(GSTRegistrationType gstRegistrationType) {
        this.gstRegistrationType = gstRegistrationType;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getIecCode() {
        return iecCode;
    }

    public void setIecCode(String iecCode) {
        this.iecCode = iecCode;
    }

    public TaxExemptionType getTaxExemptionType() {
        return taxExemptionType;
    }

    public void setTaxExemptionType(TaxExemptionType taxExemptionType) {
        this.taxExemptionType = taxExemptionType;
    }

    public String getTaxExemptionDetails() {
        return taxExemptionDetails;
    }

    public void setTaxExemptionDetails(String taxExemptionDetails) {
        this.taxExemptionDetails = taxExemptionDetails;
    }

    public LocalDate getFinancialYearStart() {
        return financialYearStart;
    }

    public void setFinancialYearStart(LocalDate financialYearStart) {
        this.financialYearStart = financialYearStart;
    }

    public LocalDate getFinancialYearEnd() {
        return financialYearEnd;
    }

    public void setFinancialYearEnd(LocalDate financialYearEnd) {
        this.financialYearEnd = financialYearEnd;
    }

    public LocalDate getBooksBeginFrom() {
        return booksBeginFrom;
    }

    public void setBooksBeginFrom(LocalDate booksBeginFrom) {
        this.booksBeginFrom = booksBeginFrom;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Boolean getMultiCurrencyEnabled() {
        return multiCurrencyEnabled;
    }

    public void setMultiCurrencyEnabled(Boolean multiCurrencyEnabled) {
        this.multiCurrencyEnabled = multiCurrencyEnabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CompanyEntity() {
    }

    public CompanyEntity(Long id, String name, String alias, String mailingName, AddressEntity address, String telephone, String mobile, String email, String website, String panNumber, String cinNumber, GSTRegistrationType gstRegistrationType, String gstin, String iecCode, TaxExemptionType taxExemptionType, String taxExemptionDetails, LocalDate financialYearStart, LocalDate financialYearEnd, LocalDate booksBeginFrom, String baseCurrency, Boolean multiCurrencyEnabled, Boolean enabled)
    {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.mailingName = mailingName;
        this.address = address ;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.website = website;
        this.panNumber = panNumber;
        this.cinNumber = cinNumber;
        this.gstRegistrationType = gstRegistrationType;
        this.gstin = gstin;
        this.iecCode = iecCode;
        this.taxExemptionType = taxExemptionType;
        this.taxExemptionDetails = taxExemptionDetails;
        this.financialYearStart = financialYearStart;
        this.financialYearEnd = financialYearEnd;
        this.booksBeginFrom = booksBeginFrom;
        this.baseCurrency = baseCurrency;
        this.multiCurrencyEnabled = multiCurrencyEnabled;
        this.enabled = enabled;
    }

}
