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
    private String id;

    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "introduce", length = 500)
    private String introduce;
}
