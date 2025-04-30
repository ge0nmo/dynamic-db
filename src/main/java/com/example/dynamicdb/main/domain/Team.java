package com.example.dynamicdb.main.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "db_url")
    private String dbUrl;

    @Column(nullable = false, name = "db_username")
    private String dbUsername;

    @Column(nullable = false, name = "db_password")
    private String dbPassword;

    protected Team() {
    }

    @Builder
    public Team(String name, String dbUrl, String dbUsername, String password) {
        this.name = name;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = password;
    }
}
