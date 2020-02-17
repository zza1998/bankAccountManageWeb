package com.zza.jpaa.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    Map getInfo(String userId);

}
