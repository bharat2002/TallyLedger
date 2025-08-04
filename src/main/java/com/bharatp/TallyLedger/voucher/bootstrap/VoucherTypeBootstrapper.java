package com.bharatp.TallyLedger.voucher.bootstrap;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.voucher.entity.VoucherTypeEntity;
import com.bharatp.TallyLedger.voucher.repository.VoucherTypeRepository;
import com.bharatp.TallyLedger.voucher.util.VoucherTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class VoucherTypeBootstrapper {

    private VoucherTypeRepository repo;

    @Autowired
    public VoucherTypeBootstrapper(VoucherTypeRepository repo) {
        this.repo = repo;
    }

    public void initDefaultVoucherType(CompanyEntity company)
    {
        Arrays.stream(VoucherTypeEnum.values()).forEach(voucherTypeEnum -> {
                boolean ifPresent = repo.findByNameAndCompany_Id(voucherTypeEnum, company.getId()).isPresent();
                if(!ifPresent)
                {
                    VoucherTypeEntity voucherTypeEntity  = new VoucherTypeEntity();
                    voucherTypeEntity.setCompany(company);
                    voucherTypeEntity.setName(voucherTypeEnum);
                    voucherTypeEntity.setCustom(false);
                    voucherTypeEntity.setDescription("Auto-created default voucher type: " + voucherTypeEnum.name());
                    repo.save(voucherTypeEntity);
                }
        });
    }
}
