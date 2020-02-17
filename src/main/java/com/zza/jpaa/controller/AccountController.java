package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.AccountDto;
import com.zza.jpaa.entity.dto.PageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.AccountVo;
import com.zza.jpaa.entity.vo.PageSearchVo;
import com.zza.jpaa.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "账户管理模块接口")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @ApiOperation("开户")
    @PostMapping("/create")
    public ResultData create(@RequestBody AccountVo accountVo, @CurrentUser UserInfo userInfo) {
        accountService.createAccount(accountVo, userInfo);
        return ResultData.success("创建成功");
    }

    @ApiOperation("销户  /  冻结")
    @PostMapping("/status/{id}")
    public ResultData changeAccount(@PathVariable String id, @RequestParam("status") Integer status) {
        accountService.changeAccount(id,status);
        return ResultData.success("操作成功");
    }


    @ApiOperation("获取账户列表")
    @GetMapping("/list")
    public ResultData getList(PageSearchVo pageSearchVo) {
        PageDto accountDtoList = accountService.getList(pageSearchVo);
        return ResultData.success(null, accountDtoList);
    }
}
