package com.bharatp.TallyLedger.Group.service;

import com.bharatp.TallyLedger.Common.Exeption.NotFoundException;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.repository.CompanyRepository;
import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.mapper.GroupMapper;
import com.bharatp.TallyLedger.Group.repository.GroupRepository;
import com.bharatp.TallyLedger.Group.util.GroupNature;
import com.bharatp.TallyLedger.Group.util.GroupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repo;
    private final GroupMapper mapper;
    private final CompanyRepository companyRepo;

    @Autowired
    public GroupServiceImpl(GroupRepository repo, GroupMapper mapper, CompanyRepository companyRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.companyRepo = companyRepo;
    }


    @Override
    public GroupDTO createGroup(GroupDTO groupDTO)
    {
        CompanyEntity company  = companyRepo.findById(groupDTO.getId()).orElseThrow(()->
                        new NotFoundException("Company", groupDTO.getId())
                );

        Long parentId = groupDTO.getParentId();
        GroupEntity parentGroup = null;
        if(null != parentId)
        {
            parentGroup  = repo.findById(groupDTO.getId()).orElseThrow(()->
                    new NotFoundException("Company", groupDTO.getId())
            );
        }

        if(repo.existsByNameAndCompanyIdAndParentId(groupDTO.getName(),groupDTO.getId(),groupDTO.getParentId()))
        {
            throw new RuntimeException("Group Already Exists");
        }

        GroupEntity entity = mapper.toEntity(groupDTO);
        entity.setParent(parentGroup);
        entity.setCompany(company);
        return mapper.toDTO(repo.save(mapper.toEntity(groupDTO)));
    }

    @Override
    public List<GroupDTO> getAllGroups(Long companyId) {
        return repo.findByCompany_Id(companyId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public GroupDTO getGroupById(Long companyId, Long id) {
        CompanyEntity companyEntity = null;
        companyEntity  = companyRepo.findById(companyId).orElseThrow(
                ()->{
                    return new NotFoundException("Company", companyId);
                }
        );

        GroupEntity entity = repo.findByCompany_IdAndId(companyId, id).orElseThrow(
                ()->{
                    throw new NotFoundException("Group", id);
                }
        );
        return mapper.toDTO(entity);
    }

    @Override
    public GroupDTO updateGroup(Long companyId, Long id, GroupDTO dto) {
        CompanyEntity companyEntity = null;
        companyEntity  = companyRepo.findById(companyId).orElseThrow(
                ()->{
                    return new NotFoundException("Company", companyId);
                }
        );
        GroupEntity entity = repo.findByCompany_IdAndId(companyId, id).;
        mapper.updateEntityFromDto(dto,entity);

    }

    @Override
    public void deleteGroup(Long companyId, Long id) {

    }

    @Override
    public List<GroupDTO> getGroupsByNature(GroupNature nature) {
        return List.of();
    }
}
