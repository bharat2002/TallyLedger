package com.bharatp.TallyLedger.Group.repository;

import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long>
{
    boolean existsByName(String name);
    boolean existsByPanNumber(String panNumber);
    boolean existsByGstin(String gstin);
    boolean existsByCinNumber(String cinNumber);
}