package com.lib.library_management_react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.repository.BookRepository;
import com.lib.library_management_react.repository.MemberRepository;
import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.model.Member;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getById(Integer memberid) {
        return memberRepository.findById(memberid);
    }

    public Member createMember(Member member) {
        memberRepository.save(member);
        return member;
    }

    public Member updateMember(Integer id, Member memberDetails) {
        Member member = memberRepository.findById(id);
        if (member != null) {
            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            memberRepository.update(member);
        }
        return member;
    }
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }
}
