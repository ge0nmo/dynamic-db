package com.example.dynamicdb.branch.service;

import com.example.dynamicdb.branch.domain.BranchMember;
import com.example.dynamicdb.branch.repository.BranchMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BranchMemberService
{
    private final BranchMemberRepository memberRepository;

    public BranchMember findByMemberCode(String memberCode)
    {
        return memberRepository.findByMemberCode(memberCode)
                .orElse(null);
    }
}
