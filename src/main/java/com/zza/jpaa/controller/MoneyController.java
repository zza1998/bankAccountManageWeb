package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.SaveMoneyVo;
import com.zza.jpaa.services.MoneyService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public ResultData saveMoney(@RequestBody SaveMoneyVo saveMoneyVo, @CurrentUser UserInfo userInfo) {
        if (StringUtils.isEmpty(saveMoneyVo.getNum())) {
            return ResultData.fail("金额错误");
        }
        String balance =  moneyService.saveMoney(saveMoneyVo,userInfo.getUserId());
        return ResultData.success("存钱成功", balance);
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
