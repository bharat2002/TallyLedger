package com.bharatp.TallyLedger.Ledger.mapper;

import com.bharatp.TallyLedger.Ledger.DTO.LedgerDTO;
import com.bharatp.TallyLedger.Ledger.Entity.LedgerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface LedgerMapper {

    @Mappings({
            @Mapping(source = "company.id",   target = "companyId"),
            @Mapping(source = "group.id",     target = "groupId"),
            @Mapping(source = "createdAt",    target = "createdAt"),
            @Mapping(source = "updatedAt",    target = "updatedAt")
    })
    LedgerDTO toDTO(LedgerEntity entity);

    @Mappings({
            @Mapping(target = "id",              ignore = true),
            @Mapping(target = "company",         ignore = true),
            @Mapping(target = "group",           ignore = true),
            @Mapping(target = "reserved",        ignore = true),
            @Mapping(target = "createdAt",       ignore = true),
            @Mapping(target = "updatedAt",       ignore = true)
    })
    LedgerEntity toEntity(LedgerDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "company",   ignore = true),
            @Mapping(target = "group",     ignore = true),
            @Mapping(target = "reserved",  ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updateEntityFromDto(LedgerDTO dto, @MappingTarget LedgerEntity entity);
}
