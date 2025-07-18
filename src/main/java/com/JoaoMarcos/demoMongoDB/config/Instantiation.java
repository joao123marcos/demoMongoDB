package com.JoaoMarcos.demoMongoDB.config;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.JoaoMarcos.demoMongoDB.Repositories.UserRepository;
import com.JoaoMarcos.demoMongoDB.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Opcional: Limpa a coleção antes do seeding para evitar duplicatas em cada reinício
        // Cuidado: Isso apagará todos os dados existentes na coleção 'user'
        userRepository.deleteAll();

        // Carrega o arquivo JSON do classpath (deve estar em src/main/resources)
        ClassPathResource resource = new 
           ClassPathResource("cursomongodb.user.json");
        InputStream inputStream = resource.getInputStream();

        // Usa Jackson ObjectMapper para ler o JSON e mapear para uma lista de objetos User
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = Arrays.asList(
            objectMapper.readValue(inputStream, User[].class));

        // Insere a lista de usuários na coleção
        userRepository.saveAll(users);

        if (userRepository.count() > 0) {
            System.out.println("Seeding de dados de usuário concluído "+
                 "com sucesso!");  
        }else{
            System.out.println("Não foi dessa vez!!!");
        }
    }
}