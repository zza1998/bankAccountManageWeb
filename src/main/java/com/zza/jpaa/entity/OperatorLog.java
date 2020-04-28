package com.zza.jpaa.entity;


import com.zza.jpaa.enums.OperaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "operator_log")
public class OperatorLog {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "opera_type",length = 10)
    private Integer operaType;

    @Column(name = "opera_user_id")
    private String operaUserId;

    @Column(name = "opera_username",length = 50)
    private String operaUserName;

    @Column(name = "number",columnDefinition = "decimal(9,2)")
    private BigDecimal number;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

}
