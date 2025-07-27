package com.bharatp.TallyLedger.Company.mapper;

import com.bharatp.TallyLedger.Company.dto.AddressDTO;
import com.bharatp.TallyLedger.Company.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO toDTO(AddressEntity entity);
    AddressEntity toEntity(AddressDTO dto);
}

