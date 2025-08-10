package com.bharatp.TallyLedger.voucher.mapper;

import com.bharatp.TallyLedger.voucher.dto.VoucherTypeDTO;
import com.bharatp.TallyLedger.voucher.entity.VoucherTypeEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface VoucherTypeMapper
{
    @Mappings(
            {
                    @Mapping(source ="companyId", target ="company.id"),
                    @Mapping(source = "", target = "")
            }
    )
    VoucherTypeDTO toDTO(VoucherTypeDTO entity);


    @Mappings(
            {
                    @Mapping(source ="companyId", target ="company.id"),
                    @Mapping(source = "", target = "")
            }
    )
    VoucherTypeEntity toEntity(VoucherTypeDTO dto);

    @Mappings(
            {
                    @Mapping(source ="companyId", target ="company.id"),
                    @Mapping(source = "", target = "")
            }
    )
    void updateEntityFromDto(VoucherTypeDTO dto , @MappingTarget VoucherTypeEntity entity);
}
