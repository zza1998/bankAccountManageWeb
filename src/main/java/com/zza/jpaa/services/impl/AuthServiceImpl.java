package com.zza.jpaa.services.impl;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.RegisterVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.AuthService;
import com.zza.jpaa.services.RedisService;
import com.zza.jpaa.util.IdCardUtil;
import com.zza.jpaa.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    UserRepository userRepository;

    @Resource
    RedisService redisService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final static Long tenMillion = 60 * 10 * 1000L;

    private final static Long fiveMillion = 60 * 5 * 1000L;

    private User ThisCurrUser;

    @Bean
    public User getCurrUser(){
       return ThisCurrUser;
    }

    @Override
    public ResultData login(String userName, String password) {
        User currUser = userRepository.findByName(userName);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 密码加密
        if (currUser == null) {
            throw new BizException(BizCode.USER_NOT_EXIST);
        }
        if (redisService.contains(currUser.getId()+"lock")) {
            log.info("用户 {} 密码错误此数过多", currUser.getId());
            throw new BizException(BizCode.USER_LOGIN_ERROR);
        }
        if (!password.equals(currUser.getPassword())) {
            // 加入缓存 如果达到一定此数锁定IP
            redisService.increment(currUser.getId());
            if (Integer.valueOf((String) redisService.getValue(currUser.getId())) >= 5) {
                redisService.setValue(currUser.getId() + "lock", "1", fiveMillion);
            }
            throw new BizException(BizCode.USER_PASSWORD_ERROR);
        }
        // 创建token 加入缓存
        String token = JwtUtil.getToken(UserInfo.builder()
                .userId(currUser.getId())
                .name(userName)
                .password(password)
                .build());
        Boolean isLogin = redisTemplate.opsForValue()
                .setIfAbsent(currUser.getId()+":token",token,JwtUtil.expire, TimeUnit.MILLISECONDS);
        // 如果已经登录不用重新获取新的token
        if (!isLogin.booleanValue()){
           token = (String) redisTemplate.opsForValue().get(currUser.getId()+":token");
        }
        redisService.setValue(currUser.getId(), "0");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expireAt", JwtUtil.expireAt(token));
        log.info("用户登录 {}",map);
        ThisCurrUser = currUser;
        return ResultData.success("登陆成功", map);
    }

    @Override
    public ResultData register(RegisterVo registerVo) {
        if (registerVo.getPassword().length() > 16) {
            return ResultData.fail("密码必须小于16位");
        }
//        if (!IdCardUtil.isValidatedAllIdcard(registerVo.getIdCard())){
//            return ResultData.fail("身份证格式错误");
//        }
//        if (StringUtils.isEmpty(registerVo.getPhone())) {
//            retqurn ResultData.fail("手机号格式错误");
//        }
        List<User> hasUsers= userRepository.findUsersByName(registerVo.getUserName());
        if (!hasUsers.isEmpty()) {
            return ResultData.fail("用户已存在");
        }
        String md5pwd = DigestUtils.md5DigestAsHex(registerVo.getPassword().getBytes());
        User newUser = User.builder()
                .name(registerVo.getUserName())
                .createTime(new Date())
                .password(md5pwd)
                .phone(registerVo.getPhone())
                .introduction(registerVo.getIntroduction())
                .avatar(StringUtils.isEmpty(registerVo.getAvatar())?
                        "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif":
                        registerVo.getAvatar())
                .role(1)
                .build();
        userRepository.save(newUser);
        return ResultData.success("注册成功");
    }
}
