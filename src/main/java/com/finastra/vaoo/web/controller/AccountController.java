package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.account.AccountService;
import com.finastra.vaoo.web.model.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") long id) {
        return new ResponseEntity<>(
                accountService.getAccountById(id)
                        .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id))
                        ), OK);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accounts = accountService.getAccounts();
        if (!accounts.isEmpty()) {
            return new ResponseEntity<>(accounts, OK);
        } else {
            throw new EntityNotFoundException("There're no accounts in db");
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(
                accountService.createAccount(accountDto), CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDto>> search (@RequestParam(required = false) Long id){
        return new ResponseEntity(accountService.search(id), OK);
    }

}
