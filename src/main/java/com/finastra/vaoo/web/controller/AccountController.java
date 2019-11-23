package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.domain.account.source.Source;
import com.finastra.vaoo.service.account.AccountService;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.mappers.account.source.WalletSourceMapper;
import com.finastra.vaoo.web.mappers.account.source.BankSourceMapper;
import com.finastra.vaoo.web.model.account.AccountDto;
import com.finastra.vaoo.web.model.account.source.BankSourceDto;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import com.finastra.vaoo.web.model.account.source.SourceDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable("id") long id) {
        Optional<AccountDto> accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(
                accountService.getAccountById(id)
                        .orElseThrow(NoSuchElementException::new
                        ), FOUND);
    }

    @GetMapping({"/", ""})
    ResponseEntity<List<AccountDto>> getAccountById() {
        List<Account> accounts = accountService.getAccounts();
        if (!accounts.isEmpty()){
            return new ResponseEntity<>(accounts.stream().map(accountMapper::toDto).collect(Collectors.toList()), OK);
        } else {
            throw new NoSuchElementException("There're no accounts in db");
        }
    }

    @PostMapping({"/", ""})
    ResponseEntity<AccountDto> createUser(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(
                accountService.createAccount(accountMapper.toEntity(accountDto)), CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable long id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @PostMapping("/test")
    ResponseEntity<Source> testInheritance(@RequestBody SourceDto source){
        if (source instanceof BankSourceDto) {
            return  new ResponseEntity<>(Mappers.getMapper(BankSourceMapper.class).toEntity((BankSourceDto) source), OK);
        } else {
            return  new ResponseEntity<>(Mappers.getMapper(WalletSourceMapper.class).toEntity((WalletSourceDto) source), OK);
        }
    }
}
