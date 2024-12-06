package com.lib.library_management_react.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Member")
public class Member {

    @Id
    private int memberid;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfMembership;

    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfMembership = LocalDateTime.now();
    }

    public Member() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.dateOfMembership = LocalDateTime.now();
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDateTime dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member [memberid=" + memberid + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", dateOfMembership=" + dateOfMembership + "]";
    }
}