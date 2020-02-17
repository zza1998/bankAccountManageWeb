package com.zza.jpaa.entity;


import com.zza.jpaa.enums.BankEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_account")
public class BankAccount implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "bank_code", nullable = false)
    private BankEnum bankCode;

    @Column(name = "card_id",nullable = false)
    private String cardId;

    @Column(name = "balance", nullable = false, columnDefinition = "decimal(9,2)")
    private BigDecimal balance;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone", nullable = false ,length = 20)
    private String phone;

    @Column(name ="id_card", nullable = false)
    private String idCard;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "operate_id", nullable = false)
    private String operateId;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
}
