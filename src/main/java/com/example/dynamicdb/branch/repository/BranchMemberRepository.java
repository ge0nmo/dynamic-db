package com.example.dynamicdb.branch.repository;

import com.example.dynamicdb.branch.domain.BranchMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchMemberRepository extends JpaRepository<BranchMember, Long>
{
    @Query("SELECT m FROM BranchMember m WHERE m.memberCode = :memberCode")
    Optional<BranchMember> findByMemberCode(@Param("memberCode") String memberCode);
}
