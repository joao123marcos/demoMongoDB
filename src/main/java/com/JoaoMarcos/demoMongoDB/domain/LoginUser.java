package com.JoaoMarcos.demoMongoDB.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.JoaoMarcos.demoMongoDB.Enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document
public class LoginUser implements UserDetails{
 
    @Id
    private String idLoginUser;
    private String loginUser;
    private String passwordUser;
    private UserRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*Se o usuário for admin retorna as duas roles dele */
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), 
            new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            /*Senão, retorna só a role de user */
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }
    @Override
    public String getUsername() {
        return this.loginUser;
    }
}
