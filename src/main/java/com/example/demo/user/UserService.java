package com.example.demo.user;

import com.example.demo.SecurityConfig;
import com.example.demo.question.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final PasswordEncoder passwordEncoder;

    public void create(String name, String password, String email){
        SiteUser siteUser = new SiteUser();
        siteUser.setName(name);
        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);

        userRepository.save(siteUser);
    }
    public SiteUser getUser(String name){
        Optional<SiteUser> siteUser = userRepository.findByName(name);
        if (siteUser.isPresent()){
            return siteUser.get();
        }else{
            throw new DataNotFoundException("site user not found");
        }

    }
}
