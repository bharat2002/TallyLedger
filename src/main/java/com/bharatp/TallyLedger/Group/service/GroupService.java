package com.bharatp.TallyLedger.Group.service;

import com.bharatp.TallyLedger.Common.Exeption.NotFoundException;
import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.mapper.GroupMapper;
import com.bharatp.TallyLedger.Group.repository.GroupRepository;
import com.bharatp.TallyLedger.Group.util.DuplicateCompanyException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository repo;
    private final GroupMapper mapper;

    @Autowired
    public GroupService(GroupRepository repo, GroupMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public GroupDTO create(@Valid GroupDTO dto) {
        if (repo.existsByName(dto.getName())) {
            throw new DuplicateCompanyException("name", dto.getName());
        }

        if (dto.getPanNumber() != null && repo.existsByPanNumber(dto.getPanNumber())) {
            throw new DuplicateCompanyException("PAN", dto.getPanNumber());
        }

        if (dto.getGstin() != null && repo.existsByGstin(dto.getGstin())) {
            throw new DuplicateCompanyException("GSTIN", dto.getGstin());
        }

        if (dto.getCinNumber() != null && repo.existsByCinNumber(dto.getCinNumber())) {
            throw new DuplicateCompanyException("CIN", dto.getCinNumber());
        }

        GroupEntity entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public List<GroupDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public GroupDTO findById(Long id) {
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Company", id));
    }

    public GroupDTO update(@Valid GroupDTO dto) {
        GroupEntity existing = repo.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Company", dto.getId()));

        mapper.updateEntityFromDto(dto, existing);

        GroupEntity saved = repo.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Company", id);
        }
        repo.deleteById(id);
    }
}
