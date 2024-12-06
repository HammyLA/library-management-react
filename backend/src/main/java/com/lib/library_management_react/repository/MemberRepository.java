package com.lib.library_management_react.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.lib.library_management_react.model.Member;

@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM Member", new MemberRowMapper());
    }

    public Member findById(Integer memberid) {
        return jdbcTemplate.queryForObject("SELECT * FROM Member WHERE memberid = ?", new MemberRowMapper(), memberid);
    }

    public int save(Member member) {
        return jdbcTemplate.update("INSERT INTO Member (first_name, last_name, email) VALUES (?, ?, ?)", member.getFirstName(), member.getLastName(), member.getEmail());
    }

    public int update(Member member) {
        return jdbcTemplate.update("UPDATE Member SET first_name = ?, last_name = ?, email = ?", member.getFirstName(), member.getLastName(), member.getEmail());
    }

    public int deleteById(Integer memberid) {
        return jdbcTemplate.update("DELETE FROM Member WHERE memberid = ?", memberid);
    }

    private static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberid(rs.getInt("memberid"));
            member.setFirstName(rs.getString("first_name"));
            member.setLastName(rs.getString("last_name"));
            member.setEmail(rs.getString("email"));
            member.setDateOfMembership((rs.getTimestamp("date_of_membership")).toLocalDateTime());
            return member;
        }
    }
}
