package com.lib.library_management_react.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.lib.library_management_react.model.Member;

/**
 * Repository class for performing database operations on the Member table.
 */
@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Spring's JDBC template for interacting with the database.

    /**
     * Retrieves all members from the Member table.
     * 
     * @return a list of all members in the database.
     */
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM Member", new MemberRowMapper());
    }

    /**
     * Retrieves a specific member by their ID.
     * 
     * @param memberid the ID of the member to retrieve.
     * @return the Member object if found, otherwise null.
     */
    public Member findById(Integer memberid) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Member WHERE memberid = ?", new MemberRowMapper(),
                    memberid);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no member is found with the given ID.
        }
    }

    /**
     * Finds members by their first or last name. If no name is provided, retrieves
     * all members.
     * 
     * @param name the name to search for (partial matches allowed).
     * @return a list of members matching the name or all members if name is empty.
     */
    public List<Member> findByName(String name) {
        if (!(name == null || name.isEmpty())) {
            return jdbcTemplate.query("SELECT * FROM Member WHERE first_name LIKE ? OR last_name LIKE ?",
                    new MemberRowMapper(), "%" + name + "%", "%" + name + "%");
        } else {
            return findAll(); // If name is null or empty, return all members.
        }
    }

    /**
     * Saves a new member to the database.
     * 
     * @param member the Member object to be saved.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int save(Member member) {
        return jdbcTemplate.update("INSERT INTO Member (first_name, last_name, email) VALUES (?, ?, ?)",
                member.getFirstName(), member.getLastName(), member.getEmail());
    }

    /**
     * Updates an existing member in the database.
     * 
     * @param member the Member object containing updated information.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int update(Member member) {
        return jdbcTemplate.update("UPDATE Member SET first_name = ?, last_name = ?, email = ? WHERE memberid = ?",
                member.getFirstName(),
                member.getLastName(), member.getEmail(), member.getMemberid());
    }

    /**
     * Deletes a member from the database by their ID.
     * 
     * @param memberid the ID of the member to delete.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int deleteById(Integer memberid) {
        return jdbcTemplate.update("DELETE FROM Member WHERE memberid = ?", memberid);
    }

    /**
     * Checks if a member has rented any books by their ID.
     * 
     * @param memberid the ID of the member to check.
     * @return a list of members who have rented books matching the criteria.
     */
    public List<Member> hasRented(Integer memberid) {
        return jdbcTemplate.query(
                "SELECT * FROM Member WHERE memberid = ? AND memberid IN (SELECT member FROM Rental)",
                new MemberRowMapper(), memberid);
    }

    /**
     * RowMapper implementation for mapping rows from the Member table to Member
     * objects.
     */
    private static final class MemberRowMapper implements RowMapper<Member> {
        /**
         * Maps a row from the ResultSet to a Member object.
         * 
         * @param rs     the ResultSet containing the data.
         * @param rowNum the current row number.
         * @return a Member object populated with data from the current row.
         * @throws SQLException if a database access error occurs.
         */
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberid(rs.getInt("memberid")); // Map the member ID.
            member.setFirstName(rs.getString("first_name")); // Map the first name.
            member.setLastName(rs.getString("last_name")); // Map the last name.
            member.setEmail(rs.getString("email")); // Map the email address.
            member.setDateOfMembership((rs.getTimestamp("date_of_membership")).toLocalDateTime()); // Map the membership
                                                                                                   // date.
            return member;
        }
    }
}
