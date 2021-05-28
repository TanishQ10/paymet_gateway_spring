package com.example.payments_gateway1.controller;

import com.example.payments_gateway1.entity.CollectData;
import com.example.payments_gateway1.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantController {

    @Autowired
    Services services;

    @PostMapping("/merchant/register")
    public String registerMerchant(@RequestBody CollectData merchantData) {
        return services.registerMerchant(merchantData);
    }

    @RequestMapping(value = "/merchant/accept",method = RequestMethod.POST)
    public String merchantAccept(Authentication authentication) {

        return services.merchantSetAccepting(authentication.getName());
    }
}
