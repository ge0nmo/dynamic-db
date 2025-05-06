package com.example.dynamicdb.branch.controller.dto;

import com.example.dynamicdb.branch.domain.GameScore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameScoreDTO
{
    private long id;
    private int score;
    private String memberCode;

    public GameScoreDTO(GameScore gameScore, String memberCode)
    {
        this.id = gameScore.getId();
        this.memberCode = memberCode;
        this.score = gameScore.getScore();
    }
}
