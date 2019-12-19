package com.zza.jpaa.controller;

import com.zza.jpaa.entity.User;
import com.zza.jpaa.respository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @PostMapping()
    public User saveUser(@RequestBody User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    @GetMapping("/name")
    public User getUser(@RequestParam String username){
        System.out.println(username);
        return userRepository.findByUserName(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userRepository.deleteById(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        user.setId(userId);
        return userRepository.saveAndFlush(user);
    }




    @PostMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }

}
