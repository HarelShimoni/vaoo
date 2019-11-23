package com.finastra.vaoo.service.account;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.web.model.account.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountById(long id);
    AccountDto createAccount(Account account);
    List<Account> getAccounts();
    void deleteAccount(long id);
}
