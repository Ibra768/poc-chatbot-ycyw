package com.ycyw.chatbot.controller;

import com.ycyw.chatbot.dto.DBUserDTO;
import com.ycyw.chatbot.service.user.IDBUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IDBUserService dbUserService;
    public AuthController(IDBUserService dbUserService) {
        this.dbUserService = dbUserService;
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = "application/json")
    public DBUserDTO login(@RequestBody DBUserDTO user)
    {
        return dbUserService.login(user);
    }
}
