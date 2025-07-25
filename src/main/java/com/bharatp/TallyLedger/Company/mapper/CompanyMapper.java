package com.bharatp.TallyLedger.Company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.dto.CompanyDTO;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO toDTO(CompanyEntity entity);
    CompanyEntity toEntity(CompanyDTO dto);
}
