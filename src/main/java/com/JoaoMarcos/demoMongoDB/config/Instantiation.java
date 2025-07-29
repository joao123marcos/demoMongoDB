package com.JoaoMarcos.demoMongoDB.config;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random; // Importar Random para seleção aleatória
import java.util.stream.Collectors; // Importar Collectors

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.JoaoMarcos.demoMongoDB.DTO.AuthorDTO; 
import com.JoaoMarcos.demoMongoDB.Repositories.PostRepository;
import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.Post; 
import com.JoaoMarcos.demoMongoDB.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; // Importar SerializationFeature para lidar com datas
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Importar JavaTimeModule
import com.fasterxml.jackson.databind.DeserializationFeature; // Importar para ignorar campos desconhecidos

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        /* Limpa a coleção de usuários antes do seeding para evitar 
        duplicatas em cada reinício*/
        userRepository.deleteAll();

        // Carrega o arquivo JSON do classpath (deve estar em src/main/resources)
        ClassPathResource userResource = 
          new ClassPathResource("cursomongodb.user.json");

        InputStream userInputStream = userResource.getInputStream();

        /*Usa Jackson ObjectMapper para ler o JSON e mapear para uma 
        lista de objetos user */
        ObjectMapper objectMapperForUsers = new ObjectMapper();
        List<User> users = Arrays.asList(
                objectMapperForUsers.readValue(userInputStream, 
                User[].class));

        // Insere a lista de usuários na coleção
        userRepository.saveAll(users);

        if (userRepository.count() > 0) {
            System.out.println("Seeding de dados de usuário concluído com sucesso!");
        } else {
            System.out.println("Não foi dessa vez para usuários!!!");
        }

        List<User> persistedUsers = userRepository.findAll();
        if (persistedUsers.isEmpty()) {
            System.err.println(
                    "Erro: Nenhum usuário foi persistido no banco de dados. "+
                    "Os posts não terão autores válidos.");
            return; // Interrompe se não houver usuários para associar
        }

        /////////////////////// Seção do post //////////////////////////////
        postRepository.deleteAll();

        ClassPathResource postResource = 
          new ClassPathResource("cursomongodb.post.json");

        InputStream postInputStream = postResource.getInputStream();

        // Configura ObjectMapper para lidar com tipos de data/hora do Java 8
        ObjectMapper objectMapperForPosts = new ObjectMapper();
        objectMapperForPosts.registerModule(new JavaTimeModule());
        objectMapperForPosts.disable(
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        /*IMPORTANTE: Ignorar campos não reconhecidos, como "authorPost" do JSON,
         pois queremos atribuí-lo manualmente*/
        objectMapperForPosts.configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Post> postsFromJson = Arrays.asList(
                objectMapperForPosts.readValue(postInputStream, 
                Post[].class));

        Random random = new Random(); // Para selecionar um autor aleatoriamente

        /*Itera sobre os posts deserializados e define a data dinamicamente e 
        associa um autor*/
        List<Post> postsToSave = postsFromJson.stream().map(post -> {
            post.setDate(LocalDateTime.now()); // Define a data atual

            /*Atribui um autor da lista de usuários persistidos.
              Para garantir que cada post tenha um autor válido e que o 
              AuthorDTO seja criado corretamente.*/
            User selectedUser = persistedUsers.get(
                random.nextInt(persistedUsers.size()));

            post.setAuthorPost(new AuthorDTO(selectedUser)); 

            return post;
        }).collect(Collectors.toList());

        // Insere a lista de posts na coleção
        postRepository.saveAll(postsToSave);

        if (postRepository.count() > 0) {
            System.out.println("Seeding de dados de post concluído com sucesso!");
        } else {
            System.out.println("Não foi dessa vez para posts!!!");
        }

        /////////////////Referenciando os posts na coleção user
        
        List<Post> listPost = postRepository.findAll();

        int limit = Math.min(persistedUsers.size(), listPost.size());

        for (int i = 0; i < limit; i++) {
            /*if (persistedUsers.get(i).getListPost() == null) {
                persistedUsers.get(i).setListPost(new java.util.ArrayList<>());
            }*/
            persistedUsers.get(i).getListPost().add(listPost.get(i));
            userRepository.save(persistedUsers.get(i)); 
        }
    }
}