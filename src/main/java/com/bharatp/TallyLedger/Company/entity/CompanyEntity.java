package com.bharatp.TallyLedger.Company.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gstNumber;
    private String address;
    private LocalDate financialYearStart;
    private LocalDate financialYearEnd;
    private String baseCurrency;
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

    public CompanyEntity(String name, String gstNumber, String address, LocalDate financialYearStart, LocalDate financialYearEnd, String baseCurrency)
    {
        this.name = name;
        this.gstNumber = gstNumber;
        this.address = address;
        this.financialYearStart = financialYearStart;
        this.financialYearEnd = financialYearEnd;
        this.baseCurrency = baseCurrency;
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

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
