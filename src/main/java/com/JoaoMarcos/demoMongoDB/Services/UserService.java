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

    public void deleteUser(String idUserDelete){
        if (idUserDelete != null){
           Optional<User> userMongoDB = userRepository.findById(idUserDelete);
           if (userMongoDB.isPresent()) {
              userRepository.deleteById(idUserDelete);
            }else{
                throw new RuntimeException("Id user not exist");
            }     
        }else{
            throw new RuntimeException("Id user invalid");
        }
    }

    public void updateUser(User user){
        Optional<User> userMongoDB = userRepository.findById(user.getId());
        if (userMongoDB.isPresent()) {
            User newUser = userMongoDB.get();
            updateDataUser(newUser, user);
        }else{
            throw new RuntimeException("Object user not found");
        }
    }

    private void updateDataUser(User userNew, User user) {
        userNew.setEmail(user.getEmail());
        userNew.setNome(user.getNome());

        userRepository.save(userNew);
    }
}
