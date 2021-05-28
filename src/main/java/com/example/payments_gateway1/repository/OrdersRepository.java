package com.example.payments_gateway1.repository;

import com.example.payments_gateway1.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
