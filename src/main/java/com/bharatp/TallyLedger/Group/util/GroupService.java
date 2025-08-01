package com.bharatp.TallyLedger.Group.util;

import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.util.GroupNature;

import java.util.List;


public interface GroupService {
    GroupDTO createGroup(Long companyId, GroupDTO groupDTO);
    List<GroupDTO> getAllGroups();

    List<GroupDTO> getAllGroups(Long companyId);

    GroupDTO getGroupById(Long companyId, Long id);

    GroupDTO updateGroup(Long companyId, Long id, GroupDTO dto);

    void deleteGroup(Long companyId, Long id);

    List<GroupDTO> getGroupsByNature(GroupNature nature);
}
