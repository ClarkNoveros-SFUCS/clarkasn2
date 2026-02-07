package com.clarkasn2.clarkasn2.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="usersTable")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int uid;

    @NotBlank(message = "Fill up name")
    private String name;

    private Role rolyType;

    private int clarity;

    private int niceness;

    private int knowledgeableScore;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
