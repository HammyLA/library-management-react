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
    private LocalDateTime dateOfMembership;

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Members [memberid=" + memberid + ", firstName=" + firstName + ", lastName=" + lastName
                + ", dateOfMembership=" + dateOfMembership + "]";
    }
}
