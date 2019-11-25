package com.finastra.vaoo.service.account;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.repository.AccountRepository;
import com.finastra.vaoo.repository.specification.AccountDaoSpecification;
import com.finastra.vaoo.repository.specification.Operation;
import com.finastra.vaoo.repository.specification.SearchCriteria;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.model.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public AccountDto createAccount(AccountDto account) {
        return accountMapper.toDto(
                accountRepository.save(
                        accountMapper.toEntity(account)
                )
        );
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

    @Override
    public List<AccountDto> search(Long id) {
        List<Account> l = accountRepository.findAll(new AccountDaoSpecification(new SearchCriteria("id", Operation.EQUALS, id)));
        return l.stream().map(a -> accountMapper.toDto(a)).collect(Collectors.toList());
    }
}
