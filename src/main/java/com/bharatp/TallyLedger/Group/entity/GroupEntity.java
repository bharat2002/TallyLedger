package com.bharatp.TallyLedger.Group.entity;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Group.util.GroupNature;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(
        name = "account_groups",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"name", "company_id", "parent_id"}
        )
)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private GroupEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<GroupEntity> children;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private GroupNature nature;

    @Column(nullable = false)
    private boolean affectsGrossProfit;

    @Column(nullable = false)
    private boolean reserved = false;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    public static GroupEntityBuilder builder() {
        return new GroupEntityBuilder();
    }

    @PreUpdate
    public void touchUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public GroupEntity(Long id, String name, GroupEntity parent, Set<GroupEntity> children, CompanyEntity company, GroupNature nature, boolean affectsGrossProfit, boolean reserved, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.company = company;
        this.nature = nature;
        this.affectsGrossProfit = affectsGrossProfit;
        this.reserved = reserved;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public static class GroupEntityBuilder
    {
        private CompanyEntity company;
        private GroupEntity parent;
        private String name;
        private GroupNature nature;
        private boolean affectsGrossProfit;
        private boolean reserved;

        public GroupEntityBuilder setCompany(CompanyEntity company) {
            this.company = company;
            return this;
        }

        public GroupEntityBuilder setParent(GroupEntity parent) {
            this.parent = parent;
            return this;
        }

        public GroupEntityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GroupEntityBuilder setNature(GroupNature nature) {
            this.nature = nature;
            return this;
        }

        public GroupEntityBuilder setAffectsGrossProfit(boolean affectsGrossProfit) {
            this.affectsGrossProfit = affectsGrossProfit;
            return this;
        }

        public GroupEntityBuilder setResrved(boolean resrved) {
            this.reserved = resrved;
            return this;
        }

        public GroupEntity build()
        {
            GroupEntity entity = new GroupEntity();
            entity.setCompany(this.company);
            entity.setParent(this.parent);
            entity.setName(this.name);
            entity.setNature(this.nature);
            entity.setAffectsGrossProfit(this.affectsGrossProfit);
            entity.setReserved(this.reserved);
            entity.setCreatedAt(Instant.now());
            entity.setUpdatedAt(Instant.now());
            return entity;
        }
    }

    public GroupEntity() {
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

    public GroupEntity getParent() {
        return parent;
    }

    public void setParent(GroupEntity parent) {
        this.parent = parent;
    }

    public Set<GroupEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<GroupEntity> children) {
        this.children = children;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public GroupNature getNature() {
        return nature;
    }

    public void setNature(GroupNature nature) {
        this.nature = nature;
    }

    public boolean isAffectsGrossProfit() {
        return affectsGrossProfit;
    }

    public void setAffectsGrossProfit(boolean affectsGrossProfit) {
        this.affectsGrossProfit = affectsGrossProfit;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
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
}
