package com.bharatp.TallyLedger.Group.dto;

import com.bharatp.TallyLedger.Group.util.GroupNature;
import jakarta.validation.constraints.*;

public class GroupDTO {

    private Long id;

    @NotBlank(message = "Group name must not be blank")
    @Size(min = 2, max = 100, message = "Group name length must be between 2 and 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9 _-]+$", message = "Group name may only contain letters, numbers, spaces, hyphens, and underscores")
    private String name;

    @Min(value = 1, message = "parentId must be a positive number")
    private Long parentId;

    @NotNull(message = "Group nature is required")
    private GroupNature nature;

    private Boolean affectsGrossProfit = false;

    private Boolean reserved = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name != null ? name.trim() : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public GroupNature getNature() {
        return nature;
    }

    public void setNature(GroupNature nature) {
        this.nature = nature;
    }

    public Boolean getAffectsGrossProfit() {
        return affectsGrossProfit;
    }

    public void setAffectsGrossProfit(Boolean affectsGrossProfit) {
        this.affectsGrossProfit = affectsGrossProfit;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
