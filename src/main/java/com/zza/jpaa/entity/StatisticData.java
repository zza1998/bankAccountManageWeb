package com.zza.jpaa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistic_data")
public class StatisticData {


    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "all_user")
    private Integer allUser;

    @Column(name = "user_with_account")
    private Integer usersWithAccount;

    @Column(name = "add_account")
    private Integer addAccount;

    @Column(name = "all_account")
    private Integer allAccount;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
}
