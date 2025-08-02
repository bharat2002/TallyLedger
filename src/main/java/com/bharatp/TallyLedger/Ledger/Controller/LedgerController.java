package com.bharatp.TallyLedger.Ledger.Controller;

import com.bharatp.TallyLedger.Ledger.DTO.LedgerDTO;
import com.bharatp.TallyLedger.Ledger.Service.LedgerService;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/ledgers")
public class LedgerController {
    private final LedgerService ledgerService;

    @Autowired
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping
    public ResponseEntity<LedgerDTO> createLedger(@PathVariable Long companyId, @RequestBody LedgerDTO dto) {
        LedgerDTO created = ledgerService.createLedger(companyId, dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<LedgerDTO>> getAllLedgers(@PathVariable Long companyId) {
        return ResponseEntity.ok(ledgerService.getAllLedgers(companyId));
    }

    @GetMapping("/{ledgerId}")
    public ResponseEntity<LedgerDTO> getLedgerById(@PathVariable Long companyId, @PathVariable Long ledgerId) {
        return ResponseEntity.ok(ledgerService.getLedgerById(companyId, ledgerId));
    }

    @PutMapping("/{ledgerId}")
    public ResponseEntity<LedgerDTO> updateLedger(@PathVariable Long companyId,
                                                  @PathVariable Long ledgerId,
                                                  @RequestBody LedgerDTO dto) {
        return ResponseEntity.ok(ledgerService.updateLedger(companyId, ledgerId, dto));
    }

    @DeleteMapping("/{ledgerId}")
    public ResponseEntity<Void> deleteLedger(@PathVariable Long companyId, @PathVariable Long ledgerId) {
        ledgerService.deleteLedger(companyId, ledgerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<LedgerDTO>> getLedgersByGroup(@PathVariable Long companyId,
                                                             @PathVariable Long groupId) {
        return ResponseEntity.ok(ledgerService.getLedgersByGroup(companyId, groupId));
    }

    @GetMapping("/nature/{nature}")
    public ResponseEntity<List<LedgerDTO>> getLedgersByNature(@PathVariable Long companyId,
                                                              @PathVariable LedgerNature nature) {
        return ResponseEntity.ok(ledgerService.getLedgersByNature(companyId, nature));
    }
}
