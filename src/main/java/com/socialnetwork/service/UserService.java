package com.socialnetwork.service;

import com.socialnetwork.dto.response.UserProfileResponse;
import com.socialnetwork.exception.ResourceNotFoundException;
import com.socialnetwork.mapper.UserMapper;
import com.socialnetwork.model.entity.UserProfile;
import com.socialnetwork.repository.UserProfileRepository;
import com.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService{
  private final UserRepository userRepository;
  private final UserProfileRepository profileRepository;
  private final UserMapper userMapper;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
  
  public List<UserProfileResponse> getAllProfiles() {
    return profileRepository.findAll()
      .stream()
      .map(userMapper::toProfileResponse)
      .toList();
  }
  
  public UserProfileResponse getProfileByUsername(String username) {
    UserProfile profile = profileRepository.findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException("User profile not found: " + username));
      
    return userMapper.toProfileResponse(profile);
  }
}
