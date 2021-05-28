package com.example.payments_gateway1.repository;

import com.example.payments_gateway1.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {

    Optional<UserData> findByUsername(String username);

    Optional<UserData> findByEmail(String email);
}
