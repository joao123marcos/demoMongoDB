package com.JoaoMarcos.demoMongoDB.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.JoaoMarcos.demoMongoDB.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
