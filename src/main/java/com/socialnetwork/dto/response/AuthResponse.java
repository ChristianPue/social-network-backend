package com.socialnetwork.dto.response;

public record AuthResponse(
  String token,
  String type,
  String username,
  String email,
  String role
) {
  public AuthResponse(String token, String username, String email, String role) {
    this(token, "Bearer", username, email, role);
  }
}
