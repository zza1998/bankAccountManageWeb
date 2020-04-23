package com.zza.jpaa.services.impl;

import com.zza.jpaa.entity.Role;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.UserInfoDto;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.RoleRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Override
    public UserInfoDto getInfo(String userId) {
        Optional<User> opUser = userRepository.findById(userId);
        if (opUser.isPresent()){
            User user = opUser.get();
            Role role = roleRepository.findById(user.getRole())
                    .orElse(Role.builder().code("admin").build());

            return UserInfoDto.builder().avatar(user.getAvatar())
                    .introdution(user.getIntroduction())
                    .name(user.getName())
                    .roles(role.getCode())
                    .build();
        }else {
            throw new BizException("用户信息暂无");
        }
    }
}
