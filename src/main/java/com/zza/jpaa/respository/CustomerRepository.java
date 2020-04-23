package com.zza.jpaa.respository;

import com.zza.jpaa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
}
