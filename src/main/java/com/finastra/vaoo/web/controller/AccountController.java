package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.service.account.AccountService;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.model.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") long id) {
        Optional<AccountDto> accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(
                accountService.getAccountById(id)
                        .orElseThrow(NoSuchElementException::new
                        ), FOUND);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<AccountDto>> getAccountById() {
        List<Account> accounts = accountService.getAccounts();
        if (!accounts.isEmpty()){
            return new ResponseEntity<>(accounts
                    .stream()
                    .map(ac -> getMapper(AccountMapper.class).toDto(ac))
                    .collect(Collectors.toList()), OK);
        } else {
            throw new NoSuchElementException("There're no accounts in db");
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<AccountDto> createUser(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(
                accountService.createAccount(getMapper(AccountMapper.class).toEntity(accountDto)), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(ACCEPTED).build();
    }

}
