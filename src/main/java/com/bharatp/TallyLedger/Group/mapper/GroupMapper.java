package com.bharatp.TallyLedger.Group.mapper;

import com.bharatp.TallyLedger.Group.dto.GroupMappingDTO;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);


    GroupMappingDTO toDTO(GroupEntity entity);

    @Mappings( {
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    GroupEntity toEntity(GroupMappingDTO dto);

    void updateEntityFromDto(GroupMappingDTO dto, @MappingTarget GroupEntity entity);
}
