package com.bharatp.TallyLedger.voucher.dto;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.voucher.util.VoucherTypeEnum;
import jakarta.validation.constraints.NotNull;

public class VoucherTypeDTO {
    private Long id;
    @NotNull
    private VoucherTypeEnum name;

    @NotNull
    private boolean isCustom;

    @NotNull
    private Long companyId;

    public VoucherTypeDTO(Long id, VoucherTypeEnum name, boolean isCustom, Long companyId, String description) {
        this.id = id;
        this.name = name;
        this.isCustom = isCustom;
        this.companyId = companyId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public VoucherTypeEnum getName() {
        return name;
    }

    public void setName(VoucherTypeEnum name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    private String description;
}
