package com.finastra.vaoo.service;

import com.finastra.vaoo.domain.Account;
import com.finastra.vaoo.repository.AccountRepository;
import com.finastra.vaoo.web.mappers.AccountMapper;
import com.finastra.vaoo.web.model.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Optional<AccountDto> getAccountById(long id){
        return accountRepository.findById(id).map(ac -> accountMapper.toDto(ac));
    }

    @Override
    public AccountDto createAccount(Account account){
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }
}
