package com.example.payments_gateway1.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant_data")
public class MerchantData extends Data {

    private String GSTIN;
    private boolean is_accepting;

}
