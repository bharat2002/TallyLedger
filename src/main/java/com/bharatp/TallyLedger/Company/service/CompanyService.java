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
    public final CompanyRepository repo;
    public final CompanyMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository repo, CompanyMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public  CompanyDTO create(CompanyDTO dto)
    {
        CompanyEntity entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public List<CompanyDTO> findAll()
    {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public CompanyDTO findbyId(Long Id)
    {
        return repo.findById(Id).map(mapper::toDTO).orElseThrow(()-> new  NotFoundException("Company", Id));
    }

    public CompanyDTO update(@Valid CompanyDTO dto)
    {
        CompanyEntity existing  = repo.findById(dto.getId()).orElseThrow(()-> new NotFoundException("Company",dto.getId()));
        existing.setName(dto.getName());
        existing.setGstNumber(dto.getGstNumber());
        existing.setAddress(dto.getAddress());
        existing.setFinancialYearStart(dto.getFinancialYearStart());
        existing.setFinancialYearEnd(dto.getFinancialYearEnd());
        existing.setBaseCurrency(dto.getBaseCurrency());
        existing.setEnabled(dto.getEnabled());
        return mapper.toDTO(repo.save(existing));
    }

    public void delete( Long id) {
        repo.deleteById(id);
    }
}
