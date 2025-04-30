package com.example.dynamicdb.main.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Table(name = "member")
@Entity
public class MainMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    private String name;

    @Column(name = "member_code")
    private String memberCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "team_id")
    private Team team;

    protected MainMember() {
    }

    @Builder
    public MainMember(Long id, int age, String name, Team team) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.team = team;
    }

    @Override
    public String toString()
    {
        return "MainMember{" +
                "memberCode='" + memberCode + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
