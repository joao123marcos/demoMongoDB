package com.JoaoMarcos.demoMongoDB.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.JoaoMarcos.demoMongoDB.DTO.AuthenticateUserDTO;
import com.JoaoMarcos.demoMongoDB.DTO.LoginResponseDTO;
import com.JoaoMarcos.demoMongoDB.DTO.RegisterUserDto;
import com.JoaoMarcos.demoMongoDB.Services.LoginUserService;
import com.JoaoMarcos.demoMongoDB.Services.TokenService;
import com.JoaoMarcos.demoMongoDB.domain.LoginUser;

import jakarta.validation.Valid;
import lombok.var;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public LoginUserService loginUserService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticateUserDTO data){
        
        //Cria um token a partir do dados o usuário
        var userNamePassword = new UsernamePasswordAuthenticationToken
              (data.login(), data.password());
        
        //Auntentica o usuário pelo authenticationManager
        var authenticated = authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken(
            (LoginUser)authenticated.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
        
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterUserDto data) 
    throws Exception{
        
        LoginUser u = loginUserService.insertUserLogin(data);
        
        URI uri = ServletUriComponentsBuilder.
                      fromCurrentRequest().path("/{id}").
                      buildAndExpand(u.getIdLoginUser()).toUri();
        
        return ResponseEntity.created(uri).body(u);
    
    }
}
