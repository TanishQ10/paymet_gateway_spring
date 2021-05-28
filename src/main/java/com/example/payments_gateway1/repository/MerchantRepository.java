package com.example.payments_gateway1.repository;

import com.example.payments_gateway1.entity.MerchantData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantData, Integer> {

    Optional<MerchantData> findByUsername(String username);

    Optional<MerchantData> findByEmail(String email);
}
