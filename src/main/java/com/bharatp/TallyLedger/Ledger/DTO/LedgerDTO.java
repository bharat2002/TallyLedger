package com.bharatp.TallyLedger.Ledger.DTO;

import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LedgerDTO {

    private Long id;

    @NotNull(message = "Ledger nature is required")
    private LedgerNature nature;

    @NotBlank(message = "Ledger name is required")
    @Size(max = 150, message = "Ledger name must be at most 150 characters")
    private String name;

    @Size(max = 150, message = "Alias must be at most 150 characters")
    private String alias;

    @NotNull(message = "Company ID is required")
    @Min(value = 1, message = "Company ID must be positive")
    private Long companyId;

    @NotNull(message = "Group ID is required")
    @Min(value = 1, message = "Group ID must be positive")
    private Long groupId;

    @DecimalMin(value = "0.00", message = "Opening balance cannot be negative")
    @Digits(integer = 17, fraction = 2, message = "Invalid opening balance")
    private BigDecimal openingBalance;

    private Boolean reserved = false;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
    private String pan;

    @Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]\\wZ\\w", message = "Invalid GSTIN format")
    private String gstin;

    @Size(max = 100, message = "Contact person name too long")
    private String contactPerson;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @Email(message = "Invalid email address")
    private String email;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
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
