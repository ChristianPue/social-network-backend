package com.socialnetwork.service;

import com.socialnetwork.dto.request.LoginRequest;
import com.socialnetwork.dto.request.SignupRequest;
import com.socialnetwork.dto.response.AuthResponse;
import com.socialnetwork.exception.BadRequestException;
import com.socialnetwork.model.entity.User;
import com.socialnetwork.model.entity.UserProfile;
import com.socialnetwork.repository.UserRepository;
import com.socialnetwork.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  
  @Transactional
  public AuthResponse signup(SignupRequest request) {
    validateSignupRequest(request);
    
    User user = createUser(request);
    User savedUser = userRepository.save(user);
    
    String jwtToken = jwtService.generateToken(savedUser);
    
    return new AuthResponse(jwtToken, savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole().name());
  }
  
  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );
    
    User user = userRepository.findByUsername(request.username())
      .orElseThrow(() -> new BadRequestException("User not found"));
    
    String jwtToken = jwtService.generateToken(user);
    
    return new AuthResponse(jwtToken, user.getUsername(), user.getEmail(), user.getRole().name());
  }
  
  private void validateSignupRequest(SignupRequest request) {
    if (userRepository.existsByUsername(request.username())) {
      throw new BadRequestException("Username already exists");
    }
    
    if (userRepository.existsByEmail(request.email())) {
      throw new BadRequestException("Email already exists");
    }
  }
  
  private User createUser(SignupRequest request) {
    User user = User.builder()
      .username(request.username())
      .email(request.email())
      .password(passwordEncoder.encode(request.password()))
      .build();
    
    UserProfile profile = UserProfile.builder()
      .firstName(request.firstName())
      .lastName(request.lastName())
      .user(user) 
      .build();
    
    user.setProfile(profile);
    return user;
  }
}
