package com.bharatp.TallyLedger.Group.mapper;

import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(target = "reserved", ignore = true)
    GroupDTO toDTO(GroupEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "parent", ignore = true),
            @Mapping(target = "company", ignore = true),
            @Mapping(target = "children", ignore = true),
            @Mapping(target = "reserved", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    GroupEntity toEntity(GroupDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "parent", ignore = true),
            @Mapping(target = "company", ignore = true),
            @Mapping(target = "children", ignore = true),
            @Mapping(target = "reserved", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updateEntityFromDto(GroupDTO dto, @MappingTarget GroupEntity entity);
}
