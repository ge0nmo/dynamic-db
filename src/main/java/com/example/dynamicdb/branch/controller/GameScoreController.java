package com.example.dynamicdb.branch.controller;

import com.example.dynamicdb.branch.controller.dto.GameScoreDTO;
import com.example.dynamicdb.branch.domain.GameScore;
import com.example.dynamicdb.branch.service.GameScoreService;
import com.example.dynamicdb.config.db.BranchDatabaseService;
import com.example.dynamicdb.config.db.DatabaseContextHolder;
import com.example.dynamicdb.main.domain.MainMember;
import com.example.dynamicdb.main.service.MainMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/score")
@RequiredArgsConstructor
@RestController
public class GameScoreController
{
    private final GameScoreService gameScoreService;
    private final MainMemberService mainMemberService;
    private final BranchDatabaseService branchDatabaseService;

    @GetMapping
    public ResponseEntity<GameScoreDTO> getGameScoreById(@RequestParam("memberCode") String memberCode) {
        log.info("GameScoreController.getGameScoreByMemberCode = {}", memberCode);
        MainMember member = mainMemberService.findByMemberCode(memberCode);
        log.info("Member = {}", member);

        branchDatabaseService.switchDatabase(member);
        log.info("DatabaseContextHolder.getBranch() = {}", DatabaseContextHolder.getBranch());

        GameScore gameScore = gameScoreService.getMemberGameScore(memberCode);

        branchDatabaseService.clear();
        log.info("GameScoreController.getGameScoreByMemberCode response = {}", gameScore);

        return ResponseEntity.ok(new GameScoreDTO(gameScore, memberCode));
    }
}
