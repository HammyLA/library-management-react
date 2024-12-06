package com.lib.library_management_react.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Rental;

@Repository
public class RentalRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rental> findAll() {
        return jdbcTemplate.query("SELECT * FROM Rental", new BookRowMapper());
    }

    public Rental findById(Integer rentalid) {
        return jdbcTemplate.queryForObject("SELECT * FROM Rental WHERE rentalid = ?", new BookRowMapper(), rentalid);
    }

    public List<Rental> findByRental(String rental) {
        return jdbcTemplate.query("SELECT * FROM Rental WHERE member LIKE ?", new BookRowMapper(), "%" + rental + "%");
    }

    public int save(Rental rental) {
        return jdbcTemplate.update("INSERT INTO Rental (member, book, day_of_rental, due_date) VALUES (?, ?, ?, ?)", rental.getMember(), rental.getBook(), rental.getDayOfRental(), rental.getDueDate());
    }

    public int update(Rental rental) {
        return jdbcTemplate.update("UPDATE Rental SET member = ?, book = ?, day_of_rental = ?, due_date = ? WHERE rentalid = ?", rental.getMember(), rental.getBook(), rental.getDayOfRental(), rental.getDueDate(),
                rental.getRentalid());
    }

    public int deleteById(Integer rentalid) {
        return jdbcTemplate.update("DELETE FROM Rental WHERE rentalid = ?", rentalid);
    }

    private static final class BookRowMapper implements RowMapper<Rental> {
        @Override
        public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rental book = new Rental();
            book.setRentalid(rs.getInt("rentalid"));
            book.setMember(rs.getInt("member"));
            book.setBook(rs.getInt("book"));
            book.setDayOfRental(rs.getTimestamp("day_of_rental").toLocalDateTime());
            book.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
            return book;
        }
    }
}
