package com.JoaoMarcos.demoMongoDB.DTO;

import java.io.Serializable;

import com.JoaoMarcos.demoMongoDB.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;

    public AuthorDTO(User user){
        this.id = user.getId();
        this.nome = user.getNome();
    }
}
