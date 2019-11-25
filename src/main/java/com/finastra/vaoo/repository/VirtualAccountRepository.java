package com.finastra.vaoo.repository;

import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualAccountRepository extends CrudRepository<VirtualAccount, Long> {
}
