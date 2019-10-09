package com.ss.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public void save(Object key, Object value){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    public Object get(Object key){
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}
