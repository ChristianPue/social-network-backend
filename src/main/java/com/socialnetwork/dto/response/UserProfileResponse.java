package com.socialnetwork.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserProfileResponse(
  Long id,
  String username,
  String firstName,
  String lastName,
  String bio,
  String profileImageUrl,
  LocalDate birthDate,
  String location,
  String website,
  Integer followersCount,
  Integer followingCount,
  Integer postsCount,
  Boolean isPrivate,
  LocalDateTime createdAt
) {}
