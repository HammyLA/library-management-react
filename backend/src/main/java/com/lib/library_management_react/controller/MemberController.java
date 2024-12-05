package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.repository.MemberRepository;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/{id}")
    public Member findById(@PathVariable Integer id) {
        return members.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Member> postMethodName(@RequestBody Member member) {
        Member savedMember = members.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }
}
