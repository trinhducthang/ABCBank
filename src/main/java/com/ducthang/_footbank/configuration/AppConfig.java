package com.ducthang._footbank.configuration;


import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.entity.enum_.Role;
import com.ducthang._footbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository usersRepository){
        return args -> {
            if(usersRepository.findByUsername("admin").isEmpty()){
                Role adminRole = Role.ADMIN;
                User users = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .firstName("admin")
                        .role(adminRole)
                        .build();
                usersRepository.save(users);
                log.info("ADMIN saved successfully");
            }
        };
    }
}