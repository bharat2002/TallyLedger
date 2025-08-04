package com.bharatp.TallyLedger.voucher.entity;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.voucher.util.VoucherTypeEnum;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "voucher_types",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class VoucherTypeEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherTypeEnum name;

    @Column(nullable = false)
    private boolean isCustom = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoucherTypeEnum getName() {
        return name;
    }

    public void setName(VoucherTypeEnum name) {
        this.name = name;
    }

    public VoucherTypeEntity(Long id, VoucherTypeEnum name, boolean isCustom, CompanyEntity company, String description) {
        this.id = id;
        this.name = name;
        this.isCustom = isCustom;
        this.company = company;
        this.description = description;
    }

    public VoucherTypeEntity() {
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
