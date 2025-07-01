package com.socialnetwork.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "first_name")
  private String firstName;
  
  @Column(name = "last_name")
  private String lastName;
  
  private String bio;
  
  @Column(name = "profile_image_url")
  private String profileImageUrl;
  
  @Column(name = "birth_date")
  private LocalDate birthDate;
  
  private String location;
  
  private String website;
  
  @Column(name = "followers_count")
  private Integer followersCount = 0;
  
  @Column(name = "following_count")
  private Integer followingCount = 0;
  
  @Column(name = "posts_count")
  private Integer postsCount = 0;
  
  @Column(name = "is_private")
  private Boolean isPrivate = false;
  
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
  
  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }
  
  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
