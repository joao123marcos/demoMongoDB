package com.JoaoMarcos.demoMongoDB.config;

import java.io.InputStream;
import java.time.LocalDateTime; // Importar LocalDateTime
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; // Importar Collectors

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.JoaoMarcos.demoMongoDB.Repositories.PostRepository;
import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.Post; // Importar Post
import com.JoaoMarcos.demoMongoDB.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; // Importar SerializationFeature para lidar com datas
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Importar JavaTimeModule

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        // Limpa a coleção de usuários antes do seeding para evitar duplicatas em cada reinício
        userRepository.deleteAll();

        // Carrega o arquivo JSON do classpath (deve estar em src/main/resources)
        ClassPathResource userResource = new 
          ClassPathResource("cursomongodb.user.json");
          
        InputStream userInputStream = userResource.getInputStream();

        // Usa Jackson ObjectMapper para ler o JSON e mapear para uma lista de objetos User
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = Arrays.asList(
            objectMapper.readValue(userInputStream, User[].class));

        // Insere a lista de usuários na coleção
        userRepository.saveAll(users);

        if (userRepository.count() > 0) {
            System.out.println("Seeding de dados de usuário concluído com sucesso!");
        } else {
            System.out.println("Não foi dessa vez para usuários!!!");
        }

        ///////////////////////Seção do post //////////////////////////////
        postRepository.deleteAll();

        ClassPathResource postResource = new 
            ClassPathResource("cursomongodb.post.json");

        InputStream postInputStream = postResource.getInputStream();

        // Configurandp ObjectMapper para lidar com tipos de data/hora do Java 8
        // Recria ou reconfigura o ObjectMapper para posts
        objectMapper = new ObjectMapper();
        
        // Registra o módulo para java.time
        objectMapper.registerModule(new JavaTimeModule()); 
        
        // Opcional: para formatar datas como ISO 8601 strings
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); 

        List<Post> postsFromJson = Arrays.asList(
            objectMapper.readValue(postInputStream, Post[].class));

        // Itera sobre os posts deserializados e define a data dinamicamente
        List<Post> postsToSave = postsFromJson.stream().map(post -> {
            post.setDate(LocalDateTime.now()); // Define a data atual
            return post;
        }).collect(Collectors.toList());

        // Insere a lista de posts na coleção
        postRepository.saveAll(postsToSave);

        if (postRepository.count() > 0) {
            System.out.println("Seeding de dados de post concluído com sucesso!");
        } else {
            System.out.println("Não foi dessa vez para posts!!!");
        }
    }
}