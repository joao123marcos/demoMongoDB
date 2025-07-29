package com.JoaoMarcos.demoMongoDB.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class Post implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String IdPost;
    private LocalDateTime date;
    private String titlePost;
    private String bodyPost;
    private User authorPost;
}
