package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.virtual_account.VirtualAccountService;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/vaccount")
public class VirtualAccountController {
    @Autowired
    VirtualAccountService virtualAccountService;

    @GetMapping({"/", ""})
    ResponseEntity<List<VirtualAccountDto>> getVirtualAccounts() {
        return new ResponseEntity<>(virtualAccountService.getVirtualAccounts(), OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<VirtualAccountDto> getVirtualAccountById(@PathVariable long id) {
        return new ResponseEntity<>(
                virtualAccountService
                        .getVirtualAccountById(id)
                        .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)))
                , OK);
    }

    @PostMapping({"/", ""})
    ResponseEntity<VirtualAccountDto> createVirtualAccount(@RequestBody VirtualAccountDto account) {
        return new ResponseEntity<>(virtualAccountService.createVirtualAccount(account), OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<VirtualAccountDto> deleteVirtualAccount(@PathVariable long id) {
        return new ResponseEntity<>(virtualAccountService.deleteVirtualAccount(id), OK);
    }
}
