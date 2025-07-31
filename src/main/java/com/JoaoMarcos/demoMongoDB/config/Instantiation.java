package com.JoaoMarcos.demoMongoDB.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.JoaoMarcos.demoMongoDB.DTO.AuthorDTO;
import com.JoaoMarcos.demoMongoDB.DTO.Coments;
import com.JoaoMarcos.demoMongoDB.Repositories.PostRepository;
import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.Post;
import com.JoaoMarcos.demoMongoDB.domain.User;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userReposiroty;


    @Autowired
    private PostRepository postReposiroty;

    @Override
    public void run(String... arg0) throws Exception {

        userReposiroty.deleteAll();
        postReposiroty.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, alex, bob));
        
        Post post1 = new Post(null, LocalDateTime.now(), 
         "Partiu viagem", "Vou viajar para São Paulo. Abraços!", 
         new AuthorDTO(maria));

        Post post2 = new Post(null, LocalDateTime.now(), "Bom dia", 
         "Acordei feliz hoje!", new AuthorDTO(maria));
        

        Coments c1 = new Coments(null, "Olha ele", LocalDateTime.now(),
         new AuthorDTO(bob));
         
        Coments c2 = new Coments(null, "Estou cansado", LocalDateTime.now(),
         new AuthorDTO(alex));
        
        post1.getListComents().addAll(Arrays.asList(c1));
        post2.getListComents().addAll(Arrays.asList(c2));

        postReposiroty.saveAll(Arrays.asList(post1, post2));

        maria.getListPost().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(maria);
    }

}