package com.finastra.vaoo.service.account;

import com.finastra.vaoo.web.model.account.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountById(long id);

    AccountDto createAccount(AccountDto account);

    List<AccountDto> getAccounts();

    void deleteAccount(long id);

    List<AccountDto> search(Long id);
}
