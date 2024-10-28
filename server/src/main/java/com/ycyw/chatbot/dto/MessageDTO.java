package com.ycyw.chatbot.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {
    private Long id;
    private String username;
    private String content;
    private String send_at;
}
