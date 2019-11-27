package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.virtual_account.VirtualAccountService;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
    ResponseEntity<VirtualAccountDto> createVirtualAccount(@RequestBody VirtualAccountDto virtualAccountDto, @RequestParam long account) {
        return new ResponseEntity<>(virtualAccountService.createVirtualAccount(virtualAccountDto, account), CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteVirtualAccount(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    ResponseEntity<List<VirtualAccountDto>> search(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Long id) {
        return new ResponseEntity<>(virtualAccountService.search(name, id), ACCEPTED);
    }

    @PutMapping({"", "/"})
    ResponseEntity<VirtualAccountDto> updateAccount(@RequestBody VirtualAccountDto virtualAccountDto) {
        return new ResponseEntity<>(virtualAccountService.updateVirtualAccount(virtualAccountDto), OK);
    }
}
