package com.JoaoMarcos.demoMongoDB.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.JoaoMarcos.demoMongoDB.DTO.AuthorDTO;
import com.JoaoMarcos.demoMongoDB.DTO.Coments;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Post implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String idPost;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    
    private String titlePost;
    private String bodyPost;
    private AuthorDTO authorPost;

    private List<Coments> listComents = new ArrayList<>();

    public Post(String id, LocalDateTime d, String t, String b, AuthorDTO a){
        this.idPost = id;
        this.date = d;
        this.titlePost = t;
        this.bodyPost = b;
        this.authorPost = a;
    }
}
