package com.example.payments_gateway1.controller;

import com.example.payments_gateway1.entity.CollectData;
import com.example.payments_gateway1.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private Services services;


    @PostMapping(value = "/user/register", consumes = "application/json")
    public String registerUser(@RequestBody CollectData userData) {
        return services.registerUser(userData);
    }

    @PostMapping("user/add/money")
    public String addMoney( @RequestParam float amount,Authentication authentication) {
        String userData = services.addMoney(authentication.getName(), amount);
        return userData;

    }

    @GetMapping("user/enable")
    public String enableUser(Authentication authentication) {

        return services.userSetActive(authentication.getName());
    }
}
