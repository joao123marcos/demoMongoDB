package com.JoaoMarcos.demoMongoDB.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JoaoMarcos.demoMongoDB.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User joao = new User("1", "joao", "joao@gmail.com");
        User maria = new User("2", "maria", "maria@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(joao,maria));
        return ResponseEntity.ok().body(list);
    }
    
}
