package com.JoaoMarcos.demoMongoDB.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoaoMarcos.demoMongoDB.DTO.UserDTO;
import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> findAll(){
        List<User> list = userRepository.findAll();
        return list.stream().map(x -> new UserDTO(x)).toList();

        //Uma outra maneira
        //List<UserDTO> list2 = list.stream().map(x -> new UserDTO(x)).
        // collect(Collectors.toList());
    }
    
}
