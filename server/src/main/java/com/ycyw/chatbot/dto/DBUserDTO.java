package com.ycyw.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DBUserDTO {
    private String username;
    private String last_name;
    private String first_name;
    private String birth_date;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;
    private String adress;
    private String zipcode;
    private String city;
    private String token;
    private String createdAt;
    private String updatedAt;
}