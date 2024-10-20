package com.ycyw.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DBUserDTO {
    private Long id;
    private String last_name;
    private String first_name;
    private String birth_date;
    private String email;
    private String role;
    private String adress;
    private String zipcode;
    private String city;
    private String createdAt;
    private String updatedAt;
}