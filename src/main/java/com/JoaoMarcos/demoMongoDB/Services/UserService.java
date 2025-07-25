package com.JoaoMarcos.demoMongoDB.Services;

import java.util.List;
import java.util.Optional;

import com.JoaoMarcos.demoMongoDB.Services.Execptions.NoResourceFoundException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.ObjectNotfound;
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

    public UserDTO findById(String idUser){
        if (idUser != " "){
            Optional<User> opUser = userRepository.findById(idUser);
            if (opUser.isPresent()){
                User user = opUser.get();
                return new UserDTO(user);
            }else {
                throw new ObjectNotfound(idUser);
            }
        }else {
            throw new NoResourceFoundException("Id user invalid");
        }
    }

    public User insertUser(User obj){
        if (obj != null){
             return userRepository.insert(obj);
        }else {
            throw new RuntimeException("Object invalid");
        }
    }

    public  User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getNome(), userDTO.getEmail());
    }

    public void deleteUser(User obj){
        if (obj != null){
            userRepository.deleteById(obj.getId());
        }else{
            throw new RuntimeException("Object invalid");
        }
    }
}
