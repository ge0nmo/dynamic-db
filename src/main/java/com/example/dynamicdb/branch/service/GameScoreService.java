package com.example.dynamicdb.branch.service;

import com.example.dynamicdb.branch.domain.GameScore;
import com.example.dynamicdb.branch.repository.GameScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameScoreService
{
    private final GameScoreRepository gameScoreRepository;

    public GameScore getMemberGameScore(String memberCode)
    {
        return gameScoreRepository.findByMemberCode(memberCode)
                .orElse(null);
    }
}
