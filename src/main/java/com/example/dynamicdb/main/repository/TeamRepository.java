package com.example.dynamicdb.main.repository;

import com.example.dynamicdb.main.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>
{
}
