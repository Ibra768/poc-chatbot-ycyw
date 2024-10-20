package com.ycyw.chatbot.controller;

import com.ycyw.chatbot.dto.DBUserDTO;
import com.ycyw.chatbot.dto.TokenDTO;
import com.ycyw.chatbot.jwt.JWTService;
import com.ycyw.chatbot.service.user.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomUserDetailsService userDetailsService;
    private final JWTService jwtService;
    public AuthController(CustomUserDetailsService userDetailsService, JWTService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = "application/json")
    public TokenDTO login(@RequestBody DBUserDTO user)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails.getUsername());
        return new TokenDTO(token);
    }
}
