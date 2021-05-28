package com.example.payments_gateway1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectData {

    private int user_id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String PAN;
    private String GSTIN;
    private float wallet_money = 0;

}
