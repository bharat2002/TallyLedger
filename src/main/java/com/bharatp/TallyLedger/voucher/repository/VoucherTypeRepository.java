package com.bharatp.TallyLedger.voucher.repository;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.voucher.entity.VoucherTypeEntity;
import com.bharatp.TallyLedger.voucher.util.VoucherTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherTypeRepository extends JpaRepository<VoucherTypeEntity, Long> {
    Optional<VoucherTypeRepository> findByNameAndCompany_Id(VoucherTypeEnum voucherTypeEnum, Long    companyEntity);
}
