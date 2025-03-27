package com.devconnect.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String profileImageUrl;
    private String location;
    private String linkedinUrl;
    private String githubUrl;
}
