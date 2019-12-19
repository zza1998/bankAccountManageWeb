package com.zza.jpaa.services.impl;

import com.zza.jpaa.services.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value, Long expire) {
        redisTemplate.opsForValue().set(key, value, expire);
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean contains(String key) {
        return  redisTemplate.opsForValue().get(key) != null ;
    }

    public void increment(String key){
        if (contains(key)){
                Integer num = Integer.valueOf((String) Objects.requireNonNull(redisTemplate.opsForValue().get(key)));
                num++;
                redisTemplate.opsForValue().set(key,num.toString());

        }else {
            redisTemplate.opsForValue().set(key,"1");
        }

    }
}
