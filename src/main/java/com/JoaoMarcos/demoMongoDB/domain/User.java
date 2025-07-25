package com.JoaoMarcos.demoMongoDB.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String nome;
    private String email;
}
