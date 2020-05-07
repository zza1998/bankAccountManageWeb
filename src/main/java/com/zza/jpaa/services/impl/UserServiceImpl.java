package com.zza.jpaa.services.impl;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.entity.Role;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.UserInfoDto;
import com.zza.jpaa.entity.vo.ModifyUserVo;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.RoleRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Override
    public UserInfoDto getInfo(String userId) {
        Optional<User> opUser = userRepository.findById(userId);
        if (opUser.isPresent()) {
            User user = opUser.get();
            Role role = roleRepository.findById(user.getRole())
                    .orElse(Role.builder().code("admin").build());

            return UserInfoDto.builder().avatar(user.getAvatar())
                    .introduction(user.getIntroduction())
                    .name(user.getName())
                    .roles(role.getCode())
                    .phone(user.getPhone())
                    .build();
        } else {
            throw new BizException("用户信息暂无");
        }
    }

    @Override
    public ResultData modifyUserInfo(ModifyUserVo modifyUserVo) {
        String userId = UserInfoConfig.currUserInfos.get().getUserId();
        String phone = modifyUserVo.getPhone();
        if (phone.length() != 11) {
            throw new BizException("手机号格式错误");
        }
        List<User> users = userRepository.findAll()
                .stream()
                .filter((o) -> o.getName().equals(modifyUserVo.getName()))
                .collect(Collectors.toList());
        if (users.size() > 0) {
            for (User u : users) {
                if (u.getName().equals(modifyUserVo.getName())&&!u.getId().equals(userId)){
                    throw new BizException("姓名重复");
                }
            }
        }
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            User user = byId.get();
            BeanUtils.copyProperties(modifyUserVo, user);
            userRepository.saveAndFlush(user);
        }
        return ResultData.success("修改成功");
    }
}
