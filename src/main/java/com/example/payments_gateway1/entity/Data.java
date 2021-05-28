
package com.example.payments_gateway1.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@GenericGenerator(
        name = "optimized-sequence",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                @Parameter(name = "optimizer", value = "hilo"),
                @Parameter(name = "increment_size", value = "0")})

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Data {

    @Id
    private int user_id;
    @Column(unique = true)
    private String username;
    private String name;
    @Column(unique = true)
    private String email;

    private float wallet_money = 0;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created_on;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updated_on;

}
