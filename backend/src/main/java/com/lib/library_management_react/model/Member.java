package com.lib.library_management_react.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a Member entity that maps to the "Member" table in the database.
 */
@Table("Member") // Specifies the table name associated with this entity.
public class Member {

    @Id // Indicates that this field is the primary key for the "Member" table.
    private int memberid; // Unique identifier for the member.
    private String firstName; // First name of the member.
    private String lastName; // Last name of the member.
    private String email; // Email address of the member.
    private LocalDateTime dateOfMembership; // Date and time the member joined.

    /**
     * Constructor to create a Member instance with specified details.
     * 
     * @param firstName the first name of the member.
     * @param lastName  the last name of the member.
     * @param email     the email address of the member.
     */
    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfMembership = LocalDateTime.now(); // Set the membership date to the current date and time.
    }

    /**
     * Default constructor to create an empty Member instance.
     * Fields are initialized with default values.
     */
    public Member() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.dateOfMembership = LocalDateTime.now(); // Set the membership date to the current date and time.
    }

    // Getter and Setter methods for each field.

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

    /**
     * Generates a string representation of the Member object.
     * 
     * @return a string containing the member's details.
     */
    @Override
    public String toString() {
        return "Member [memberid=" + memberid + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", dateOfMembership=" + dateOfMembership + "]";
    }
}
