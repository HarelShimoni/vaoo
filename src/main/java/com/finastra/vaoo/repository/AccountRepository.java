package com.finastra.vaoo.repository;

import com.finastra.vaoo.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
