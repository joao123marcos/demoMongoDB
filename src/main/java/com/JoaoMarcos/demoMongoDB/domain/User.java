package com.JoaoMarcos.demoMongoDB.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nome;
    private String email;
}
