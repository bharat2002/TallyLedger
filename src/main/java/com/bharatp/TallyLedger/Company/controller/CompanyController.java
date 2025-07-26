package com.bharatp.TallyLedger.Company.controller;

import com.bharatp.TallyLedger.Company.dto.CompanyDTO;
import com.bharatp.TallyLedger.Company.service.CompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService service;

    CompanyController(CompanyService service)
    {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO create(@RequestBody @Valid CompanyDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CompanyDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDTO get(@PathVariable @Min(1) Long id) {
        return service.findbyId(id);
    }

    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable @Min(1) Long id, @RequestBody @Valid CompanyDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
