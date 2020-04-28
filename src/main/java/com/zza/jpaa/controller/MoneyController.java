package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.SaveMoneyVo;
import com.zza.jpaa.services.MoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "账户金额操作")
@RestController
@RequestMapping("/money")
public class MoneyController {

    @Resource
    private MoneyService moneyService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "存钱业务")
    @PostMapping("/save")
    public ResultData saveMoney(@RequestBody SaveMoneyVo saveMoneyVo, @CurrentUser UserInfo userInfo) {
        if (StringUtils.isEmpty(saveMoneyVo.getNum())) {
            return ResultData.fail("金额错误");
        }
        if (saveMoneyVo.getNum() >= 200000) {
            return ResultData.fail("单次充值不超过20万");
        }
        String balance = moneyService.saveMoney(saveMoneyVo, userInfo.getUserId());
        return ResultData.success("存钱成功", balance);
    }


    @ApiOperation(value = "取款业务")
    @PostMapping("/reduce")
    public ResultData reduceMoney(@RequestBody SaveMoneyVo saveMoneyVo, @CurrentUser UserInfo userInfo) {
        if (StringUtils.isEmpty(saveMoneyVo.getNum())) {
            return ResultData.fail("金额错误");
        }
        if (saveMoneyVo.getNum() < 0) {
            return ResultData.fail("金额错误");
        }
        String balance = moneyService.reduceMoney(saveMoneyVo, userInfo.getUserId());
        return ResultData.success("取钱成功", balance);
    }

    @ApiOperation(value = "获取余额")
    @GetMapping("/getBalance/{cardId}")
    public ResultData getBalance(@PathVariable String cardId) {
        BalanceDto balance = moneyService.getBalance(cardId);
        return ResultData.success("查询成功", balance);
    }
}
