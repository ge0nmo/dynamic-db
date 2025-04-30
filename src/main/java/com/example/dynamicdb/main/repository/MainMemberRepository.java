package com.example.dynamicdb.main.repository;

import com.example.dynamicdb.main.domain.MainMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MainMemberRepository extends JpaRepository<MainMember, Long>
{
    @Query("SELECT m FROM MainMember m WHERE m.memberCode = :memberCode")
    Optional<MainMember> findByMemberCode(@Param("memberCode") String memberCode);
}
