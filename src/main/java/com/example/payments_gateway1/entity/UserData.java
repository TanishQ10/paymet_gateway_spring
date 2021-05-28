package com.example.payments_gateway1.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
public class UserData extends Data {
    private String PAN;
    private boolean is_active;
}
