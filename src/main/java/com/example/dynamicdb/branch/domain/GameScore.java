package com.example.dynamicdb.branch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game_score")
@Entity
public class GameScore
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private BranchMember member;

    @Override
    public String toString()
    {
        return "GameScore{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
