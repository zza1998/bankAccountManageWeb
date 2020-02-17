package com.zza.jpaa.services.impl;

import com.zza.jpaa.entity.Role;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.RoleRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Override
    public Map getInfo(String userId) {
        Map<String,Object> result = new HashMap<>();
        Optional<User> opUser = userRepository.findById(userId);
        if (opUser.isPresent()){
            User user = opUser.get();
            result.put("avatar",user.getAvatar());
            result.put("introdution",user.getIntroduction());
            result.put("name",user.getName());
            Role role = roleRepository.findById(user.getRole())
                    .orElse(Role.builder().code("admin").build());
            result.put("roles", Arrays.asList(role.getCode()));
            return result;
        }else {
            throw new BizException("用户信息暂无");
        }
    }
}
