package com.JoaoMarcos.demoMongoDB.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.JoaoMarcos.demoMongoDB.domain.LoginUser;


public interface LoginUserRepository extends MongoRepository<LoginUser, String>{

    UserDetails findByLoginUser(String loginUser);
    
}
