package com.zza.jpaa.respository;

import com.zza.jpaa.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Serializable> {

    BankAccount findBankAccountByUserIdAndBankId(String userId,String bankId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update bank_account set balance = balance + :num where id = :id",nativeQuery = true)
    void addBalance(Integer num, String id);

}
