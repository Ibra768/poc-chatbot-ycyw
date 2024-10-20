package com.ycyw.chatbot.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String last_name;
    private String first_name;
    private String birth_date;
    private String email;
    private String adress;
    private String zipcode;
    private String city;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private DBUserRole role;
}