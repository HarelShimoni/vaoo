package com.finastra.vaoo.service.account;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.repository.AccountRepository;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.model.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Optional<AccountDto> getAccountById(long id) {
        return accountRepository.findById(id).map(ac -> accountMapper.toDto(ac));
    }

    @Override
    public AccountDto createAccount(Account account) {
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<AccountDto> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(acc -> accounts.add(accountMapper.toDto(acc)));
        return accounts;
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }
}