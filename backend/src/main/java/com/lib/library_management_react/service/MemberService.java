package com.lib.library_management_react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.repository.MemberRepository;
import com.lib.library_management_react.model.Member;

/**
 * Service layer for managing Member-related operations.
 * Acts as the intermediary between the controller and the repository layers.
 */
@Service
public class MemberService {

    @Autowired // Automatically injects the MemberRepository bean.
    private MemberRepository memberRepository;

    /**
     * Retrieves all members from the database.
     * 
     * @return a list of all members.
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Retrieves a specific member by their ID.
     * 
     * @param memberid the ID of the member to retrieve.
     * @return the Member object, or null if not found.
     */
    public Member getById(Integer memberid) {
        return memberRepository.findById(memberid);
    }

    /**
     * Retrieves members whose names match a given search string.
     * 
     * @param name the search string for member names.
     * @return a list of members matching the search string.
     */
    public List<Member> getByName(String name) {
        return memberRepository.findByName(name);
    }

    /**
     * Creates a new member and saves them to the database.
     * 
     * @param member the Member object containing the details of the new member.
     * @return the created Member object.
     */
    public Member createMember(Member member) {
        memberRepository.save(member);
        return member;
    }

    /**
     * Checks if a member currently has any rentals.
     * 
     * @param memberid the ID of the member to check.
     * @return true if the member has rentals, false otherwise.
     */
    public Boolean hasRented(Integer memberid) {
        return !memberRepository.hasRented(memberid).isEmpty(); // Returns true if the member has active rentals.
    }

    /**
     * Updates the details of an existing member.
     * If the member is not found, returns null.
     * 
     * @param id            the ID of the member to update.
     * @param memberDetails the updated details of the member.
     * @return the updated Member object, or null if not found.
     */
    public Member updateMember(Integer id, Member memberDetails) {
        Member member = memberRepository.findById(id); // Fetch the existing member.
        if (member != null) { // Update only if the member exists.
            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            member.setEmail(memberDetails.getEmail());
            memberRepository.update(member); // Save the updated member to the database.
        }
        return member;
    }

    /**
     * Deletes a member by their ID.
     * 
     * @param id the ID of the member to delete.
     */
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }
}
