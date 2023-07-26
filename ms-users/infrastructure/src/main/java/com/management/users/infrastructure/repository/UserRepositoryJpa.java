package com.management.users.infrastructure.repository;

import com.management.users.infrastructure.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserJpa, Long> {

  Optional<UserJpa> findByEmailAndActiveIsTrue(String email);

}
