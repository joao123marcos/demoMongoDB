package com.JoaoMarcos.demoMongoDB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.JoaoMarcos.demoMongoDB.DTO.RegisterUserDto;
import com.JoaoMarcos.demoMongoDB.Repositories.LoginUserRepository;
import com.JoaoMarcos.demoMongoDB.domain.LoginUser;

@Service
public class LoginUserService implements UserDetailsService{

    @Autowired
    public LoginUserRepository loginUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws 
     UsernameNotFoundException {
        return loginUserRepository.findByLoginUser(username);

        /*Com este método o spring security já será capaz de receber
         * do mongo o usuário para fazer a validação.*/
    }

    public LoginUser insertUserLogin(RegisterUserDto data) throws Exception{

        if (loginUserRepository.findByLoginUser(data.login()) != null) {
            throw new Exception("User already exists");
            
        }else{
            String encodPasswordUser = new BCryptPasswordEncoder().encode(
                data.password());
            
            LoginUser u = new LoginUser(null, 
              data.login(), encodPasswordUser, 
              data.role());

            loginUserRepository.save(u);
            
            return u;
        }
    }
    
}
