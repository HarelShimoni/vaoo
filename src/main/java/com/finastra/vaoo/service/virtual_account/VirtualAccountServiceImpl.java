package com.finastra.vaoo.service.virtual_account;

import com.finastra.vaoo.repository.VirtualAccountRepository;
import com.finastra.vaoo.web.mappers.VirtualAccountMapper;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VirtualAccountServiceImpl implements VirtualAccountService{
    @Autowired
    VirtualAccountRepository virtualAccountRepository;

    @Autowired
    VirtualAccountMapper virtualAccountMapper;

    @Override
    public Optional<VirtualAccountDto> getVirtualAccountById(long id){return Optional.of(new VirtualAccountDto(1l, "acc1", "alcohol", 0.0, 0.0, true, new Date()));}

    @Override
    public VirtualAccountDto createVirtualAccount(VirtualAccountDto virtualAccountDto){
        return virtualAccountMapper.toDto(
                virtualAccountRepository.save(
                        virtualAccountMapper.toEntity(virtualAccountDto)
                )
        );
    }

    @Override
    public List<VirtualAccountDto> getVirtualAccounts(){
        return StreamSupport
                .stream(virtualAccountRepository.findAll().spliterator(),true)
                .map(virtualAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VirtualAccountDto deleteVirtualAccount(long id){return new VirtualAccountDto(id, "acc1", "alcohol", 0.0, 0.0, true, new Date());}
}
