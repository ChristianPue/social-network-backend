package com.socialnetwork.controller;

import com.socialnetwork.dto.request.LoginRequest;
import com.socialnetwork.dto.request.SignupRequest;
import com.socialnetwork.dto.response.ApiResponse;
import com.socialnetwork.dto.response.AuthResponse;
import com.socialnetwork.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
    
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest request) {
    AuthResponse response = authService.signup(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("User registered successfully", response));
  }
  
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
    AuthResponse response = authService.login(request);
    return ResponseEntity.ok(ApiResponse.success("Login successful", response));
  }
}
