package com.socialnetwork.controller;

import com.socialnetwork.dto.response.ApiResponse;
import com.socialnetwork.dto.response.UserProfileResponse;
import com.socialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
    
  @GetMapping("/profiles")
  public ResponseEntity<ApiResponse<List<UserProfileResponse>>> getAllProfiles() {
    List<UserProfileResponse> profiles = userService.getAllProfiles();
    return ResponseEntity.ok(ApiResponse.success("Profiles retrieved successfully", profiles));
  }
  
  @GetMapping("/profiles/{username}")
  public ResponseEntity<ApiResponse<UserProfileResponse>> getProfileByUsername(@PathVariable String username) {
    UserProfileResponse profile = userService.getProfileByUsername(username);
    return ResponseEntity.ok(ApiResponse.success("Profile retrieved successfully", profile));
  }
}
