package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.services.MoneyService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/money")
public class MoneyController {

    @Resource
    private MoneyService moneyService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/save")
    @IgnoreSecurity
    public ResultData saveMoney(@RequestParam(value = "num") Integer num, @CurrentUser UserInfo userInfo) {
        if (num == null || num < 0) {
            return ResultData.fail("金额错误");
        }
        BigDecimal balance =  moneyService.saveMoney(num,userInfo.getUserId(),"cc2cc");
        return ResultData.success("存钱成功,当前余额"+balance,balance);
    }


//    public ResultData fenBuLock(){
//       Boolean result =  stringRedisTemplate.opsForValue().setIfAbsent("lock","lock");
//        if (result){
//            return ResultData.success();
//        }
//        else{
//            return ResultData.fail();
//        }
//    }
    @PostMapping("/getBalance")
    public ResultData getBalance(@CurrentUser UserInfo userInfo){
        List<BalanceDto> balance = moneyService.getBalance(userInfo.getUserId());
        return ResultData.success("查询成功",balance);
    }
}
