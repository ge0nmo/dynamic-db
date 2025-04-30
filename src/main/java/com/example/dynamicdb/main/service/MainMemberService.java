package com.example.dynamicdb.main.service;

import com.example.dynamicdb.main.domain.MainMember;
import com.example.dynamicdb.main.repository.MainMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MainMemberService
{
    private final MainMemberRepository mainMemberRepository;

    public MainMember findByMemberCode(String memberCode)
    {
        log.info("MainMemberService.findByMemberCode={}", memberCode);
        return mainMemberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new RuntimeException("main member not found"));
    }
}
