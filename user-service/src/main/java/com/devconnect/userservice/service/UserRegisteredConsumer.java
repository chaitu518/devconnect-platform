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

    @KafkaListener(topics = "user-registered", groupId = "user-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleUserRegistered(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent: {}", event);

        // Create user profile on registration
        UserProfile profile = UserProfile.builder()
                .id(event.getId())
                .username(event.getUsername())
                .email(event.getEmail())
                .build();

        userProfileRepository.save(profile);
    }
}
