package com.finastra.vaoo.service.virtual_account;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import com.finastra.vaoo.repository.AccountRepository;
import com.finastra.vaoo.repository.VirtualAccountRepository;
import com.finastra.vaoo.repository.specification.SearchCriteria;
import com.finastra.vaoo.repository.specification.VirtualDaoSpecification;
import com.finastra.vaoo.web.mappers.VirtualAccountMapper;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.finastra.vaoo.repository.specification.Operation.EQUALS;
import static com.finastra.vaoo.repository.specification.Operation.LIKE;

@Service
@Transactional
public class VirtualAccountServiceImpl implements VirtualAccountService {
    @Autowired
    VirtualAccountRepository virtualAccountRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    VirtualAccountMapper virtualAccountMapper;

    @Override
    public Optional<VirtualAccountDto> getVirtualAccountById(long id) {
        return virtualAccountRepository.findById(id).map(virtualAccountMapper::toDto);
    }

    @Override
    public VirtualAccountDto createVirtualAccount(VirtualAccountDto virtualAccountDto, long account) {
        VirtualAccount vacc = virtualAccountRepository.save(virtualAccountMapper.toEntity(virtualAccountDto));
        Account acc = accountRepository.findById(account).orElseThrow(() -> new EntityNotFoundException("Master account not found"));
        acc.getVirtualAccounts().add(vacc);
        return virtualAccountMapper.toDto(vacc);
    }

    @Override
    public VirtualAccountDto updateVirtualAccount(VirtualAccountDto virtualAccountDto){
        virtualAccountMapper.toEntity(virtualAccountDto).equals(virtualAccountRepository.findById(virtualAccountDto.getId()));
        return virtualAccountMapper.toDto(virtualAccountRepository.save(virtualAccountMapper.toEntity(virtualAccountDto)));
    }

    @Override
    public List<VirtualAccountDto> getVirtualAccounts() {
        return StreamSupport
                .stream(virtualAccountRepository.findAll().spliterator(), true)
                .map(virtualAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVirtualAccount(long id) {
        virtualAccountRepository.deleteById(id);
    }

    @Override
    public List<VirtualAccountDto> search(String name, Long id) {
        List<VirtualAccount> l = virtualAccountRepository.findAll(
                new VirtualDaoSpecification(new SearchCriteria("name", LIKE, name))
                        .and(new VirtualDaoSpecification(new SearchCriteria("id", EQUALS, id)))
        );
        return l.stream().map(virtualAccountMapper::toDto).collect(Collectors.toList());
    }

}
