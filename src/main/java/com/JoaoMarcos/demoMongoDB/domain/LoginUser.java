package com.JoaoMarcos.demoMongoDB.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.JoaoMarcos.demoMongoDB.Enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "idLoginUser")
@NoArgsConstructor
@AllArgsConstructor
@Document
public class LoginUser implements UserDetails{
 
    @Id
    private String idLoginUser;
    private String loginUser;
    
   /*
    * A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) é a solução
    * profissional: ela permite que a senha seja recebida em um JSON (como no 
    * cadastro), mas proíbe que ela seja enviada em uma resposta
    */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordUser;
    private UserRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*Se o usuário for admin retorna as duas roles dele */
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"), 
            new SimpleGrantedAuthority("USER"));
        }else{
            /*Senão, retorna só a role de user */
            return List.of(new SimpleGrantedAuthority("USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.passwordUser;
    }

    @Override
    public String getUsername() {
        return this.loginUser;
    }
}
