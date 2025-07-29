package com.bharatp.TallyLedger.Group.mapper;

import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);


    GroupDTO toDTO(GroupEntity entity);

    @Mappings( {
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    GroupEntity toEntity(GroupDTO dto);

    void updateEntityFromDto(GroupDTO dto, @MappingTarget GroupEntity entity);
}
