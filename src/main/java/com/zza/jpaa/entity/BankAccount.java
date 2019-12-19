package com.zza.jpaa.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "bank_id", nullable = false)
    private String bankId;

    @Column(name = "balance", nullable = false, columnDefinition = "decimal(9,2)")
    private BigDecimal balance;

    @Column(name ="user_id", nullable = false)
    private String userId;

}
