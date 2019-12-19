package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.vo.LoginVo;
import com.zza.jpaa.entity.vo.RegisterVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.services.AuthService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @IgnoreSecurity
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginVo loginVo, HttpServletRequest request , HttpServletResponse response)
    {
        System.out.println(request);
        System.out.println(loginVo);
        if (StringUtils.isEmpty(loginVo.getUserName()) || StringUtils.isEmpty(loginVo.getPassword())) {
            throw new BizException(BizCode.USER_MSG_NULL);
        }
        return authService.login(loginVo.getUserName(), loginVo.getPassword());
    }

    @IgnoreSecurity
    @PostMapping("/register")
    public ResultData register(@RequestBody RegisterVo registerVo){
        return authService.register(registerVo);
    }
}
