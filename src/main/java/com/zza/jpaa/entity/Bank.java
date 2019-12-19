package com.zza.jpaa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "bank")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Bank implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;
}
