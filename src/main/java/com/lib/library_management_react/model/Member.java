package com.lib.library_management_react.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Member {

    @Id
    private int memberid;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfMembership;

    public Member(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
        this.dateOfMembership = LocalDateTime.now();
    }

    public Member(String fname, String lname, LocalDateTime DOM) {
        this.firstName = fname;
        this.lastName = lname;
        this.dateOfMembership = DOM;
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

    @Override
    public String toString() {
        return "Members [memberid=" + memberid + ", firstName=" + firstName + ", lastName=" + lastName
                + ", dateOfMembership=" + dateOfMembership + "]";
    }
}
