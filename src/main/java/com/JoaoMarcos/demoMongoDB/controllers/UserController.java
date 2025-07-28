package com.JoaoMarcos.demoMongoDB.controllers;

import java.net.URI;
import java.util.List;

import com.JoaoMarcos.demoMongoDB.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.JoaoMarcos.demoMongoDB.DTO.UserDTO;
import com.JoaoMarcos.demoMongoDB.Services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{idUser}")
    public ResponseEntity<UserDTO> findById(@PathVariable String idUser){
        UserDTO userDTO = userService.findById(idUser);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDTO objDto){
        User user = userService.fromDTO(objDto);
        user = userService.insertUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable String idUser){
        userService.deleteUser(idUser);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idUser}")
    public ResponseEntity<Void> updateUser(@PathVariable String idUser, 
     @RequestBody UserDTO objUser){

        User user = userService.fromDTO(objUser);
        user.setId(idUser);
        userService.updateUser(user);

        return ResponseEntity.noContent().build();
    }
    
}
