package com.JoaoMarcos.demoMongoDB.DTO;

import java.io.Serializable;

import com.JoaoMarcos.demoMongoDB.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO  implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String email;

    public UserDTO(User u){
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
    }
}
