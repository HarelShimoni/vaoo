package com.finastra.vaoo.repository;

import com.finastra.vaoo.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface UserRepository extends CrudRepository <User,UUID> {
}
