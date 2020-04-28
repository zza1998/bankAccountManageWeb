package com.zza.jpaa.task;

import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.StatisticData;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.StatisticDataRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Slf4j
@Component
public class AccountTask {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    StatisticDataRepository statisticDataRepository;

    @Scheduled(cron = "0 0 0 0/1 * ?")
    public void statisticAccountInfo(){
        log.info("========================开始统计平台账号信息====================");
        Date yesterDay = DateUtil.yesterDayBefore();
        List<BankAccount> allList = bankAccountRepository.findAllList();
        Long addAccount = allList.stream().filter((o)-> o.getCreateTime().compareTo(yesterDay)>0).count();
        List<User> allUser = userRepository.findAll();
        Long addUsers = allUser.stream().filter((o)-> o.getCreateTime().compareTo(yesterDay)>0).count();
        StatisticData build = StatisticData.builder()
                .addAccount(addAccount)
                .allAccount((long) allList.size())
                .allUser((long) allUser.size())
                .addUser(addUsers)
                .createTime(new Date())
                .build();
        statisticDataRepository.save(build);
        log.info("========================统计完毕平台账号信息====================");
    }
}
