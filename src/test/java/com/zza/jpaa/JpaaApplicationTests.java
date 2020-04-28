package com.zza.jpaa;


import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.impl.BankAccountRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaaApplicationTests {

    @Autowired
    BankAccountRepositoryImpl bankAccountRepository;

    @Test
    @Transactional
    public void contextLoads() {
        List<StatisticAccountByBankDto> accountByBank = bankAccountRepository.findAccountByBank();
        System.out.println(accountByBank);
    }


}
