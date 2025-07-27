package com.bharatp.TallyLedger.Company.dto;



import com.bharatp.TallyLedger.Company.util.ValidFinancialYear;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@ValidFinancialYear
public class CompanyDTO {
    private Long id;

    @NotBlank (message = "Name must not be blank")
    private String name;

    @NotBlank(message = "GST Number is mandatory")
    @Pattern(
            regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}",
            message = "GST must follow the format 29ABCDE1234F1Z5"
    )
    private String gstNumber;

    @NotBlank(message = "Address must not be null")
    private String address;

    @NotNull(message = "Year Start should not be empty.")
    private LocalDate financialYearStart;

    @NotNull(message = "Year End should not be empty.")
    private LocalDate financialYearEnd;

    @NotEmpty(message = "Base Currency is required.")
    private String baseCurrency;

    private Boolean enabled = true;

    // Getters and setters
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