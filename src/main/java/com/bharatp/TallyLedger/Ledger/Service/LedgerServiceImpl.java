package com.bharatp.TallyLedger.Ledger.Service;

import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.repository.CompanyRepository;
import com.bharatp.TallyLedger.Common.Exeption.NotFoundException;
import com.bharatp.TallyLedger.Ledger.mapper.LedgerNatureMapper;
import com.bharatp.TallyLedger.Ledger.util.DuplicateLedgerException;
import com.bharatp.TallyLedger.Group.entity.GroupEntity;
import com.bharatp.TallyLedger.Group.repository.GroupRepository;
import com.bharatp.TallyLedger.Ledger.DTO.LedgerDTO;
import com.bharatp.TallyLedger.Ledger.Entity.LedgerEntity;
import com.bharatp.TallyLedger.Ledger.mapper.LedgerMapper;
import com.bharatp.TallyLedger.Ledger.Repository.LedgerRepository;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepo;
    private final CompanyRepository companyRepo;
    private final GroupRepository groupRepo;
    private final LedgerMapper mapper;

    @Autowired
    public LedgerServiceImpl(LedgerRepository ledgerRepo,
                             CompanyRepository companyRepo,
                             GroupRepository groupRepo,
                             LedgerMapper mapper) {
        this.ledgerRepo   = ledgerRepo;
        this.companyRepo  = companyRepo;
        this.groupRepo    = groupRepo;
        this.mapper       = mapper;
    }

    @Override
    public LedgerDTO createLedger(Long companyId, LedgerDTO dto) {
        CompanyEntity company = companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));

        GroupEntity group = groupRepo.findById(dto.getGroupId())
                .orElseThrow(() -> new NotFoundException("Group", dto.getGroupId()));
        if (!group.getCompany().getId().equals(companyId)) {
            throw new IllegalArgumentException("Group does not belong to company");
        }

        if (ledgerRepo.existsByNameAndCompany_Id(dto.getName(), companyId)) {
            throw new DuplicateLedgerException("Ledger", dto.getName());
        }

        LedgerEntity entity = mapper.toEntity(dto);
        entity.setCompany(company);
        entity.setGroup(group);
        entity.setNature(LedgerNatureMapper.fromGroupNature(group.getNature()));
        LedgerEntity saved = ledgerRepo.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public List<LedgerDTO> getAllLedgers(Long companyId) {
        companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));
        return ledgerRepo.findByCompany_Id(companyId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public LedgerDTO getLedgerById(Long companyId, Long ledgerId) {
        companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));

        LedgerEntity ledger = ledgerRepo.findByIdAndCompany_Id(ledgerId, companyId)
                .orElseThrow(() -> new NotFoundException("Ledger", ledgerId));

        return mapper.toDTO(ledger);
    }

    @Override
    public List<LedgerDTO> getLedgersByGroup(Long companyId, Long groupId) {
        companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));

        groupRepo.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Group", groupId));

        return ledgerRepo.findByGroup_IdAndCompany_Id(groupId, companyId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<LedgerDTO> getLedgersByNature(Long companyId, LedgerNature nature) {
        companyRepo.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));

        return ledgerRepo.findByNatureAndCompany_Id(nature, companyId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public LedgerDTO updateLedger(Long companyId, Long ledgerId, LedgerDTO dto) {
        LedgerEntity existing = ledgerRepo.findByIdAndCompany_Id(ledgerId, companyId)
                .orElseThrow(() -> new NotFoundException("Ledger", ledgerId));

        if (existing.isReserved()) {
            throw new IllegalStateException("Cannot modify reserved ledger: " + ledgerId);
        }

        if (!existing.getName().equals(dto.getName()) &&
                ledgerRepo.existsByNameAndCompany_Id(dto.getName(), companyId)) {
            throw new DuplicateLedgerException("Ledger", dto.getName());
        }

        mapper.updateEntityFromDto(dto, existing);

        if (!existing.getGroup().getId().equals(dto.getGroupId())) {
            GroupEntity newGroup = groupRepo.findById(dto.getGroupId())
                    .orElseThrow(() -> new NotFoundException("Group", dto.getGroupId()));
            if (!newGroup.getCompany().getId().equals(companyId)) {
                throw new IllegalArgumentException("Group does not belong to company");
            }
            existing.setGroup(newGroup);
        }

        LedgerEntity saved = ledgerRepo.save(existing);
        return mapper.toDTO(saved);
    }

    @Override
    public void deleteLedger(Long companyId, Long ledgerId) {
        LedgerEntity existing = ledgerRepo.findByIdAndCompany_Id(ledgerId, companyId)
                .orElseThrow(() -> new NotFoundException("Ledger", ledgerId));

        if (existing.isReserved()) {
            throw new IllegalStateException("Cannot delete reserved ledger: " + ledgerId);
        }

        ledgerRepo.delete(existing);
    }
}
