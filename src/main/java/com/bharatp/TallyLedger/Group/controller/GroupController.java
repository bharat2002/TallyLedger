package com.bharatp.TallyLedger.Group.controller;

import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import com.bharatp.TallyLedger.Group.service.GroupServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/groups")
@Validated
public class GroupController {
    private final GroupServiceImpl service;

    @Autowired
    GroupController(GroupServiceImpl service)
    {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO create(@PathVariable @Min(1) Long companyId,@RequestBody @Valid GroupDTO dto) {
        return service.createGroup(companyId,dto);
    }

    @GetMapping
    public List<GroupDTO> list(@PathVariable @Min(1) Long companyId) {
        return service.getAllGroups(companyId);
    }

    @GetMapping("/{id}")
    public GroupDTO get(@PathVariable @Min(1) Long companyId, @PathVariable @Min(1) Long id)
    {
        return service.getGroupById(companyId,id);
    }

    @PutMapping("/{id}")
    public GroupDTO update(@PathVariable @Min(1) Long companyId,@PathVariable @Min(1) Long id, @RequestBody @Valid GroupDTO dto) {
        dto.setId(id);
        return service.updateGroup(companyId,id,dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Min(1) Long companyId, @PathVariable Long id)
    {
        service.deleteGroup(companyId,id);
    }
}
