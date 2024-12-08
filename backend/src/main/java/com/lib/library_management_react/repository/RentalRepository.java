package com.lib.library_management_react.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Rental;

/**
 * Repository class for performing database operations on the Rental table.
 */
@Repository
public class RentalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Spring's JDBC template for database interaction.

    /**
     * Retrieves all rentals from the Rental table.
     * 
     * @return a list of all rentals.
     */
    public List<Rental> findAll() {
        return jdbcTemplate.query("SELECT * FROM Rental", new BookRowMapper());
    }

    /**
     * Retrieves a specific rental by its ID.
     * 
     * @param rentalid the ID of the rental to retrieve.
     * @return the Rental object corresponding to the ID.
     */
    public Rental findById(Integer rentalid) {
        return jdbcTemplate.queryForObject("SELECT * FROM Rental WHERE rentalid = ?", new BookRowMapper(), rentalid);
    }

    /**
     * Checks if a rental is overdue by its ID.
     * 
     * @param rentalid the ID of the rental to check.
     * @return the Rental object if overdue; otherwise, null.
     */
    public Rental checkOverdue(Integer rentalid) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Rental WHERE rentalid = ? AND due_date < CURRENT_TIMESTAMP",
                    new BookRowMapper(),
                    rentalid);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no matching book is found.
        }
    }

    /**
     * Retrieves all overdue rentals.
     * 
     * @return a list of rentals that are overdue.
     */
    public List<Rental> findAllOverdue() {
        return jdbcTemplate.query("SELECT * FROM Rental WHERE due_date < CURRENT_TIMESTAMP", new BookRowMapper());
    }

    /**
     * Finds rentals by a partial match on the member field.
     * 
     * @param rental the partial rental/member information to search for.
     * @return a list of rentals matching the criteria.
     */
    public List<Rental> findByRental(String rental) {
        return jdbcTemplate.query(
                "SELECT * FROM Rental WHERE member LIKE ?",
                new BookRowMapper(),
                "%" + rental + "%");
    }

    /**
     * Saves a new rental record to the database.
     * 
     * @param rental the Rental object to save.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int save(Rental rental) {
        return jdbcTemplate.update(
                "INSERT INTO Rental (member, book, day_of_rental, due_date) VALUES (?, ?, ?, ?)",
                rental.getMember(),
                rental.getBook(),
                rental.getDayOfRental(),
                rental.getDueDate());
    }

    /**
     * Updates an existing rental record in the database.
     * 
     * @param rental the Rental object with updated information.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int update(Rental rental) {
        return jdbcTemplate.update(
                "UPDATE Rental SET member = ?, book = ?, day_of_rental = ?, due_date = ? WHERE rentalid = ?",
                rental.getMember(),
                rental.getBook(),
                rental.getDayOfRental(),
                rental.getDueDate(),
                rental.getRentalid());
    }

    /**
     * Deletes a rental record by its ID.
     * 
     * @param rentalid the ID of the rental to delete.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int deleteById(Integer rentalid) {
        return jdbcTemplate.update("DELETE FROM Rental WHERE rentalid = ?", rentalid);
    }

    /**
     * RowMapper implementation for mapping rows from the Rental table to Rental
     * objects.
     */
    private static final class BookRowMapper implements RowMapper<Rental> {

        /**
         * Maps a row from the ResultSet to a Rental object.
         * 
         * @param rs     the ResultSet containing the data.
         * @param rowNum the current row number.
         * @return a Rental object populated with data from the current row.
         * @throws SQLException if a database access error occurs.
         */
        @Override
        public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rental book = new Rental();
            book.setRentalid(rs.getInt("rentalid")); // Map the rental ID.
            book.setMember(rs.getInt("member")); // Map the member ID.
            book.setBook(rs.getInt("book")); // Map the book ID.
            book.setDayOfRental(rs.getTimestamp("day_of_rental").toLocalDateTime()); // Map the rental date.
            book.setDueDate(rs.getTimestamp("due_date").toLocalDateTime()); // Map the due date.
            return book;
        }
    }
}
