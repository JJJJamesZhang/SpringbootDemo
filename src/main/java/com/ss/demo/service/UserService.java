package com.ss.demo.service;

import com.ss.demo.entity.User;
import com.ss.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUser(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
