package com.ycyw.chatbot.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthException extends RuntimeException{
    private String message;
}