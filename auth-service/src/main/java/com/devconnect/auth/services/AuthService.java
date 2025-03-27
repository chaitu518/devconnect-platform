package com.devconnect.auth.services;

import com.devconnect.auth.dtos.UserRegisteredEvent;
import com.devconnect.auth.models.User;
import com.devconnect.auth.repos.UserRepository;
import com.devconnect.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final KafkaTemplate<String,UserRegisteredEvent> kafkaTemplate;

    public String register(String username,String email, String password) {
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("Email already exists");
        }
        User user =User.builder().username(username).email(email).password(passwordEncoder.encode(password)).build();
        User savedUser = userRepository.save(user);
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
        kafkaTemplate.send("user.registered.v9", event);
        return jwtUtil.generateToken(email);
    }
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        passwordEncoder.matches(password, user.getPassword());
        return jwtUtil.generateToken(email);
    }
}
