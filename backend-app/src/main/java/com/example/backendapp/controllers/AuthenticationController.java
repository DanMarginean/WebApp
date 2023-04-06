package com.example.backendapp.controllers;


import com.example.backendapp.DTOs.AuthenticationRequest;
import com.example.backendapp.DTOs.AuthenticationResponse;
import com.example.backendapp.DTOs.RegisterRequest;
import com.example.backendapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}

