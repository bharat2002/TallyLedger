package com.bharatp.TallyLedger.Company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.dto.CompanyDTO;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);


    CompanyDTO toDTO(CompanyEntity entity);

    @Mappings( {
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    CompanyEntity toEntity(CompanyDTO dto);

    void updateEntityFromDto(CompanyDTO dto, @MappingTarget CompanyEntity entity);
}
