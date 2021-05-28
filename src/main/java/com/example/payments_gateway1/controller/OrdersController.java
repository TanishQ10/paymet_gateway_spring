package com.example.payments_gateway1.controller;


import com.example.payments_gateway1.entity.Orders;
import com.example.payments_gateway1.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersController {

    @Autowired
    Services services;

    @PostMapping("transaction/initiate")
    public String initiateTxn(@RequestBody Orders order, Authentication authentication) {
        return services.initiateTxn(order,authentication);
    }

    @GetMapping("transaction/status")
    public String getStatus(@RequestParam Integer id) {
        return services.getStatus(id);
    }
}
