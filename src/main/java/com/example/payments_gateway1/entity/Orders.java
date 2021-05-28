package com.example.payments_gateway1.entity;

import com.example.payments_gateway1.Enums.TxnStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_table")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer txnId;
    private TxnStatus status;
    private Integer userId;
    private Integer merchantId;
    private Float amount;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created_on;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updated_on;


}
