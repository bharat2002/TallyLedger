package com.bharatp.TallyLedger.Ledger.Service;

import com.bharatp.TallyLedger.Ledger.DTO.LedgerDTO;
import com.bharatp.TallyLedger.Ledger.util.LedgerNature;

import java.util.List;

public interface LedgerService {
    LedgerDTO createLedger(Long companyId, LedgerDTO dto);
    List<LedgerDTO> getAllLedgers(Long companyId);
    LedgerDTO getLedgerById(Long companyId, Long ledgerId);
    List<LedgerDTO> getLedgersByGroup(Long companyId, Long groupId);
    List<LedgerDTO> getLedgersByNature(Long companyId, LedgerNature nature);
    LedgerDTO updateLedger(Long companyId, Long ledgerId, LedgerDTO dto);
    void deleteLedger(Long companyId, Long ledgerId);
}
