package com.zza.jpaa;

import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.UserRepository;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaaApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void contextLoads() {

        }


}
