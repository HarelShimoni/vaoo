package com.finastra.vaoo.service.virtual_account;

import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;

import java.util.List;
import java.util.Optional;

public interface VirtualAccountService {
    Optional<VirtualAccountDto> getVirtualAccountById(long id);

    VirtualAccountDto createVirtualAccount(VirtualAccountDto accountDto, long account);

    List<VirtualAccountDto> getVirtualAccounts();

    void deleteVirtualAccount(long id);

    List<VirtualAccountDto> search(String name, Long id);
}
