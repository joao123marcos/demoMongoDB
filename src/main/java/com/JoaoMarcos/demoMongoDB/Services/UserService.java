package com.JoaoMarcos.demoMongoDB.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }
    
}
