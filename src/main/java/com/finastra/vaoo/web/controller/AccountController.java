package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.AccountService;
import com.finastra.vaoo.web.model.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable("id") String id){
        return new ResponseEntity<>(new AccountDto(
                "21", "bank", "new"
        ), HttpStatus.FOUND);
    }
}
