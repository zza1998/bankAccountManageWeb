package com.zza.jpaa.services;

public interface RedisService {

    void setValue(String key, Object value, Long expire);

    void setValue(String key, Object value);

    Object getValue(String key);

    void increment(String key);

    boolean contains(String key);
}
