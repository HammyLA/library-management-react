package com.lib.library_management_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * GET /api/members: Retrieve all members in the library.
     * 
     * @return an iterable list of all members.
     */
    @GetMapping
    public Iterable<Member> findAll() {
        return members.getAllMembers();
    }

    /**
     * GET /api/members/{id}: Retrieve a member by their ID.
     *
     * @param id the ID of the member to retrieve.
     * @return the member with the specified ID, or null if not found.
     */
    @GetMapping("/{id}")
    public Member findById(@PathVariable Integer id) {
        return members.getById(id);
    }

    /**
     * GET /api/members/search: Search for members by their name.
     *
     * @param name the name of the member to search for.
     * @return an iterable list of members that match the given name.
     */
    @GetMapping("/search")
    public Iterable<Member> findByName(@RequestParam String name) {
        return members.getByName(name);
    }

    /**
     * GET /api/members/{id}/exists: Check if a member exists by their ID.
     *
     * @param id the ID of the member to check.
     * @return true if the member exists, false otherwise.
     */
    @GetMapping("/{id}/exists")
    public Boolean isValidMember(@PathVariable Integer id) {
        return members.getById(id) != null;
    }

    /**
     * GET /api/members/{id}/status: Check if a member has any active rentals.
     *
     * @param id the ID of the member to check.
     * @return true if the member has any rentals, false otherwise.
     */
    @GetMapping("/{id}/status")
    public Boolean hasRental(@PathVariable Integer id) {
        return members.hasRented(id);
    }

    /**
     * POST /api/members: Create a new member.
     *
     * @param member the member object to be created.
     * @return a response entity with the created member and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Member> postMember(@RequestBody Member member) {
        Member savedMember = members.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    /**
     * DELETE /api/members/{id}: Delete a member by their ID, if they have no active rentals.
     *
     * @param id the ID of the member to be deleted.
     * @return a response entity with HTTP status 200 (OK) if successful, or an error message
     *         with HTTP status 400 (Bad Request) if the member has active rentals.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
        if (!members.hasRented(id)) {
            members.deleteMember(id);
            return ResponseEntity.ok().build();
        } else {
            String errorMessage = "Member deletion failed: ";
            errorMessage += "Member " + members.getById(id) + " has outstanding rental";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.trim());
        }
    }
}
