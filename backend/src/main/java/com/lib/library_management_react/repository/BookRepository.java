package com.lib.library_management_react.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository class for performing database operations on the Book table.
 */
@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieves all books from the Book table.
     * 
     * @return a list of all books in the database.
     */
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    /**
     * Retrieves a specific book by its ID.
     * 
     * @param bookid the ID of the book to be retrieved.
     * @return the Book object if found, otherwise null.
     */
    public Book findById(Integer bookid) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE bookid = ?", new BookRowMapper(), bookid);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no matching book is found.
        }
    }

    /**
     * Finds books based on a title filter and a list of genres.
     * 
     * @param title  the title filter, books with titles matching this string will
     *               be retrieved.
     * @param genres the list of genres to filter books.
     * @return a list of books matching the title and genres filters.
     */
    public List<Book> findByFilter(String title, List<String> genres) {
        if (genres.isEmpty()) {
            // If no genres are provided, filter only by title.
            return jdbcTemplate.query("SELECT * FROM Book WHERE title LIKE ?", new BookRowMapper(), "%" + title + "%");
        } else {
            // Dynamically construct the SQL query for multiple genres.
            String sql = "SELECT * FROM Book WHERE title LIKE ? AND genre IN ("
                    + String.join(",", Collections.nCopies(genres.size(), "?")) + ")";

            List<Object> params = new ArrayList<>();
            params.add("%" + title + "%");
            params.addAll(genres);

            // Execute the query with the generated SQL and parameters.
            return jdbcTemplate.query(sql, new BookRowMapper(), params.toArray());
        }
    }

    /**
     * Checks if a book with the given ID is currently rented.
     * 
     * @param bookid the ID of the book to check.
     * @return a list of books that match the criteria.
     */
    public List<Book> isRented(Integer bookid) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE bookid = ? AND bookid IN (SELECT book FROM Rental)",
                new BookRowMapper(), bookid);
    }

    /**
     * Saves a new book to the database.
     * 
     * @param book the Book object to be saved.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int save(Book book) {
        return jdbcTemplate.update(
                "INSERT INTO Book (title, author, genre, description, year_published) VALUES (?, ?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getYearPublished());
    }

    /**
     * Updates an existing book in the database.
     * 
     * @param book the Book object containing updated information.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int update(Book book) {
        return jdbcTemplate.update(
                "UPDATE Book SET title = ?, author = ?, genre = ?, description = ?, year_published = ? WHERE bookid = ?",
                book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getYearPublished(),
                book.getBookid());
    }

    /**
     * Deletes a book from the database by its ID.
     * 
     * @param bookid the ID of the book to delete.
     * @return the number of rows affected (should be 1 if successful).
     */
    public int deleteById(Integer bookid) {
        return jdbcTemplate.update("DELETE FROM Book WHERE bookid = ?", bookid);
    }

    /**
     * RowMapper implementation for mapping rows from the Book table to Book
     * objects.
     */
    private static final class BookRowMapper implements RowMapper<Book> {
        /**
         * Maps a row from the ResultSet to a Book object.
         * 
         * @param rs     the ResultSet containing the data.
         * @param rowNum the current row number.
         * @return a Book object populated with data from the current row.
         * @throws SQLException if a database access error occurs.
         */
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookid(rs.getInt("bookid"));
            book.setAuthor(rs.getString("author"));
            book.setGenre(rs.getString("genre"));
            book.setTitle(rs.getString("title"));
            book.setYearPublished(rs.getInt("year_published"));
            book.setDescription(rs.getString("description"));
            return book;
        }
    }
}
