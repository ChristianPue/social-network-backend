package com.socialnetwork.mapper;

import org.springframework.stereotype.Component;

import com.socialnetwork.dto.response.UserProfileResponse;
import com.socialnetwork.model.entity.UserProfile;

@Component
public class UserMapper {
  public UserProfileResponse toProfileResponse(UserProfile profile) {
    return new UserProfileResponse(
      profile.getId(),
      profile.getUser().getUsername(),
      profile.getFirstName(),
      profile.getLastName(),
      profile.getBio(),
      profile.getProfileImageUrl(),
      profile.getBirthDate(),
      profile.getLocation(),
      profile.getWebsite(),
      profile.getFollowersCount(),
      profile.getFollowingCount(),
      profile.getPostsCount(),
      profile.getIsPrivate(),
      profile.getCreatedAt()
    );
  }
}
