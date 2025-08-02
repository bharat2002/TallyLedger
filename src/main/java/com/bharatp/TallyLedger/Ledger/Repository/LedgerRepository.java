package com.bharatp.TallyLedger.Ledger.Repository;

import com.bharatp.TallyLedger.Ledger.Entity.LedgerEntity;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LedgerRepository extends JpaRepository<LedgerEntity, Long> {

    boolean existsByNameAndCompany_Id(String name, Long companyId);

    Optional<LedgerEntity> findByIdAndCompany_Id(Long id, Long companyId);

    List<LedgerEntity> findByCompany_Id(Long companyId);

    List<LedgerEntity> findByGroup_IdAndCompany_Id(Long groupId, Long companyId);

    List<LedgerEntity> findByNatureAndCompany_Id(LedgerNature nature, Long companyId);

    boolean existsByReservedTrueAndCompany_Id(Long companyId);

    boolean existsByReservedFalseAndCompany_Id(Long companyId);
}
