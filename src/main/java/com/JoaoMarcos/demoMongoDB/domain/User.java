package com.JoaoMarcos.demoMongoDB.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String email;


    public User(String id, String nome, String e){
        this.id = id;
        this.nome = nome;
        this.email = e;
    }
    
    @DBRef(lazy = true)
    private List<Post> listPost = new ArrayList<>();
}
