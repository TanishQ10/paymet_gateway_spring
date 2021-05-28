package com.example.payments_gateway1.entity;

import com.example.payments_gateway1.Enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@GenericGenerator(
        name = "optimized-sequence",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "0")})


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "login_table")
public class Login {
    @Column(unique = true)
    private String username;
    private String password;
    @Id
    @GeneratedValue(generator = "optimized-sequence")
    private Integer id;
    private Roles role;
}
