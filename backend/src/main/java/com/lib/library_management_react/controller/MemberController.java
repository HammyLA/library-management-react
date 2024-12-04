package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.repository.MemberRepository;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private final MemberRepository members;

    public MemberController(MemberRepository members) {
        this.members = members;
    }

    @GetMapping
    public Iterable<Member> findAll() {
        return members.findAll();
    }
}
