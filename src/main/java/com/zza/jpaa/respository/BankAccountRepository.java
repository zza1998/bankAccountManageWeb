package com.zza.jpaa.respository;

import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Serializable> {



    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update bank_account set balance = balance + :num where card_id = :cardId",nativeQuery = true)
    void addBalance(Integer num, String cardId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update bank_account set balance = balance - :num where card_id = :cardId",nativeQuery = true)
    void reduceBalance(Integer num, String cardId);

    @Query(value = "select * from bank_account where status=0 or status = 2",nativeQuery = true)
    List<BankAccount> findAllList();

    @Query(value = "select * from bank_account where card_id = :cardId and status = 0", nativeQuery = true)
    Optional<BankAccount> findByCardId(String cardId);

}
