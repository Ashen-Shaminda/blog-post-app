package com.ashen_dissanayake.blog.controllers;


import com.ashen_dissanayake.blog.domain.dtos.AuthResponse;
import com.ashen_dissanayake.blog.domain.dtos.LoginRequest;
import com.ashen_dissanayake.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {

   private final AuthenticationService authenticationService;

   @PostMapping
   public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
      UserDetails user = authenticationService.authenticate(
              loginRequest.getEmail(),
              loginRequest.getPassword()
      );
      AuthResponse authResponse = AuthResponse.builder()
              .token(authenticationService.generateToken(user))
              .expiresIn(86400)
              .build();

      return new ResponseEntity<>(authResponse, HttpStatus.OK);
   }
}
