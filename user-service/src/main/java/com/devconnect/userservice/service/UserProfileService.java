package com.devconnect.userservice.service;

import com.devconnect.userservice.model.UserProfile;
import com.devconnect.userservice.repo.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public Optional<UserProfile> getUserById(Long id) {
        return userProfileRepository.findById(id);
    }

    public Page<UserProfile> searchUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userProfileRepository.findAll((root, query, cb) -> {
            String pattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("username")), pattern),
                    cb.like(cb.lower(root.get("email")), pattern)
            );
        }, pageable);
    }

    public UserProfile updateProfile(Long id, UserProfile updatedUser) {
        return userProfileRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setBio(updatedUser.getBio());
            user.setProfileImageUrl(updatedUser.getProfileImageUrl());
            return userProfileRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfile createUser(UserProfile user) {
        return userProfileRepository.save(user);
    }
}
