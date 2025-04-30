package com.example.dynamicdb.branch.repository;

import com.example.dynamicdb.branch.domain.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameScoreRepository extends JpaRepository<GameScore, Long>
{
    @Query("SELECT g FROM GameScore g JOIN BranchMember m ON m.id = g.member.id " +
            "WHERE m.memberCode = :memberCode")
    Optional<GameScore> findByMemberCode(@Param("memberCode") String memberCode);
}
