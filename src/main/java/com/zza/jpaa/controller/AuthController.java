package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.vo.LoginVo;
import com.zza.jpaa.entity.vo.RegisterVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.services.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "用户身份模块",tags = "用户模块")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @ApiOperation(value = "用户登录", notes = "userLogin")
    @IgnoreSecurity
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginVo loginVo, HttpServletRequest request , HttpServletResponse response)
    {
        if (StringUtils.isEmpty(loginVo.getUsername()) || StringUtils.isEmpty(loginVo.getPassword())) {
            throw new BizException(BizCode.USER_MSG_NULL);
        }
        return authService.login(loginVo.getUsername(), loginVo.getPassword());
    }

    @PostMapping("/logout")
    public ResultData logout(){
        return ResultData.success();
    }


    @ApiOperation(value = "用户注册")
    @IgnoreSecurity
    @PostMapping("/register")
    public ResultData register(@RequestBody RegisterVo registerVo){
        return authService.register(registerVo);
    }
}
