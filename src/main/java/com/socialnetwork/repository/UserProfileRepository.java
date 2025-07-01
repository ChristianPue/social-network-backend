package com.socialnetwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialnetwork.model.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  @Query("SELECT up FROM UserProfile up JOIN up.user u WHERE u.username = :username")
  Optional<UserProfile> findByUsername(@Param("username") String username);
}
