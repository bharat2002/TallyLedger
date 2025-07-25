package com.bharatp.TallyLedger.Company.repository;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}