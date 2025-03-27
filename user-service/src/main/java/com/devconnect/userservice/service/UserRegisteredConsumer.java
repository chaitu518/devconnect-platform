package com.devconnect.userservice.service;

import com.devconnect.userservice.dto.UserRegisteredEvent;
import com.devconnect.userservice.model.UserProfile;
import com.devconnect.userservice.repo.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredConsumer {

    private final UserProfileRepository userProfileRepository;

    @KafkaListener(topics = "user.registered.v9", groupId = "user-service-group")
    public void handleUserRegistered(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent: {}", event);
        UserProfile profile = UserProfile.builder()
                .id(event.getId())
                .username(event.getUsername())
                .email(event.getEmail())
                .build();

        userProfileRepository.save(profile);
    }
}
