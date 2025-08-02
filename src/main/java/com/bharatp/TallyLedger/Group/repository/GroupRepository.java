package com.bharatp.TallyLedger.Group.repository;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.util.GroupNature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    boolean existsByNameAndCompany_IdAndParent_Id(String name, Long companyId, Long parentId);

    boolean existsByCompanyAndReservedTrue(CompanyEntity company);

    List<GroupEntity> findByCompany_Id(Long companyId);

    Optional<GroupEntity> findByCompany_IdAndId(Long companyId, Long id);

    boolean existsByParent(GroupEntity parent);

    List<GroupEntity> findByNature(GroupNature nature);

    void deleteByCompany_IdAndId(Long companyId, Long id);
    Optional<GroupEntity> findByNameAndCompany_Id(String name, Long companyId);

}
