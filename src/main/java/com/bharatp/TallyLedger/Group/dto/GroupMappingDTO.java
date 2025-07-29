package com.bharatp.TallyLedger.Group.dto;

import com.bharatp.TallyLedger.Group.util.GSTRegistrationType;
import com.bharatp.TallyLedger.Group.util.TaxExemptionType;
import com.bharatp.TallyLedger.Group.util.ValidFinancialYear;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@ValidFinancialYear
public class GroupMappingDTO {

    private Long id;

    @NotBlank(message = "Company name is required")
    private String name;

    private String alias;
    private String mailingName;

    @NotBlank(message = "PAN is mandatory")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
    private String panNumber;

    @Pattern(regexp = "[UL][0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}", message = "Invalid CIN format")
    private String cinNumber;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String mobile;

    private String telephone;
    private String website;

    private String gstin;

    private String iecCode;

    @NotNull
    private GSTRegistrationType gstRegistrationType;

    @NotNull
    private TaxExemptionType taxExemptionType;

    private String taxExemptionDetails;

    private boolean enabled = true;

    @NotNull(message = "Financial year start is required")
    private LocalDate financialYearStart;

    @NotNull(message = "Financial year end is required")
    private LocalDate financialYearEnd;

    private LocalDate booksBeginFrom;

    @NotBlank(message = "Base currency required")
    private String baseCurrency;

    private Boolean multiCurrencyEnabled;

    @NotNull
    @Valid
    private AddressDTO address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public GSTRegistrationType getGstRegistrationType() {
        return gstRegistrationType;
    }

    public void setGstRegistrationType(GSTRegistrationType gstRegistrationType) {
        this.gstRegistrationType = gstRegistrationType;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
