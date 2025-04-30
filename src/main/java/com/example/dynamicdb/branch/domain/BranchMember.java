package com.example.dynamicdb.branch.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "member")
@Getter
@Entity
public class BranchMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "member_code")
    private String memberCode;
}
