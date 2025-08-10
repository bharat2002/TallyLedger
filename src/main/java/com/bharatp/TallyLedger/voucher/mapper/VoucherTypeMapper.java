package com.bharatp.TallyLedger.voucher.mapper;

import com.bharatp.TallyLedger.voucher.dto.VoucherTypeDTO;
import com.bharatp.TallyLedger.voucher.entity.VoucherTypeEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface VoucherTypeMapper
{
    @Mappings(
            {
                    @Mapping(source ="", target ="")
            }
    )
    VoucherTypeDTO toDTO(VoucherTypeDTO entity);


    VoucherTypeEntity toEntity(VoucherTypeDTO dto);

    void updateEntityFromDto(VoucherTypeDTO dto , @MappingTarget VoucherTypeEntity entity);
}
