package com.zza.jpaa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistic_data")
public class StatisticData {


    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "add_user")
    private Long addUser;

    @Column(name = "all_user")
    private Long allUser;

    @Column(name = "user_with_account")
    private Long usersWithAccount;

    @Column(name = "add_account")
    private Long addAccount;

    @Column(name = "all_account")
    private Long allAccount;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
}
