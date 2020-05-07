package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.entity.Customer;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.dto.UserInfoDto;
import com.zza.jpaa.entity.vo.ModifyUserVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.CustomerRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserService userService;


    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        System.out.println(user);
        return userRepository.save(user);
    }



    @GetMapping("/name")
    public User getUser(@RequestParam String username){
        System.out.println(username);
        return userRepository.findByName(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userRepository.deleteById(userId);
    }

    @PostMapping("/modify")
    public ResultData updateUser(@RequestBody ModifyUserVo modifyUserVo) {
       return userService.modifyUserInfo(modifyUserVo);
    }
    @GetMapping("/info")
    public ResultData getUserInfo(@CurrentUser UserInfo userInfo){
        UserInfoDto result = userService.getInfo(userInfo.getUserId());
        return ResultData.success(null,result);
    }


    @PostMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }

//    @GetMapping("/getCustomer/{id}")
//    public Customer getCustomer(@PathVariable("id") String id){
//        Optional<Customer> byId = customerRepository.findById(id);
//        if (byId.isPresent()){
//            return byId.get();
//        }else {
//            throw new BizException(BizCode.USER_NOT_EXIST);
//
//        }
//    }
}
