package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.service.MemberService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private final MemberService members;

    public MemberController(MemberService members) {
        this.members = members;
    }

    @GetMapping
    public Iterable<Member> findAll() {
        return members.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable Integer id) {
        return members.getById(id);
    }

    @GetMapping("/search")
    public Iterable<Member> findByName(@RequestParam String name) {
        return members.getByName(name);
    }
    

    @PostMapping
    public ResponseEntity<Member> postMember(@RequestBody Member member) {
        Member savedMember = members.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Integer memberid) {
        members.deleteMember(memberid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
