package com.bharatp.TallyLedger.Group.controller;

import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.service.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@Validated
public class GroupController {
    private final GroupService service;

    @Autowired
    GroupController(GroupService service)
    {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO create(@RequestBody @Valid GroupDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<GroupDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GroupDTO get(@PathVariable @Min(1) Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public GroupDTO update(@PathVariable @Min(1) Long id, @RequestBody @Valid GroupDTO dto) {
        dto.setId(id);
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
