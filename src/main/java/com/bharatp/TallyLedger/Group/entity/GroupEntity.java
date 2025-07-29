package com.bharatp.TallyLedger.Group.entity;
import com.bharatp.TallyLedger.Group.util.GSTRegistrationType;
import com.bharatp.TallyLedger.Group.util.TaxExemptionType;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
@Entity
@Table(
        name = "account_groups",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"name", "company_id", "parent_id"}
        )
})
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

    @PreUpdate
    public void touchUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
