package com.bharatp.TallyLedger.Company.service;

import com.bharatp.TallyLedger.Common.Exeption.NotFoundException;
import com.bharatp.TallyLedger.Company.dto.CompanyDTO;
import com.bharatp.TallyLedger.Company.entity.CompanyEntity;
import com.bharatp.TallyLedger.Company.mapper.CompanyMapper;
import com.bharatp.TallyLedger.Company.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repo;
    private final CompanyMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository repo, CompanyMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public CompanyDTO create(@Valid CompanyDTO dto) {
        CompanyEntity entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public List<CompanyDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public CompanyDTO findById(Long id) {
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Company", id));
    }

    public CompanyDTO update(@Valid CompanyDTO dto) {
        CompanyEntity existing = repo.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Company", dto.getId()));

        mapper.updateEntityFromDto(dto, existing);

        CompanyEntity saved = repo.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Company", id);
        }
        repo.deleteById(id);
    }
}
