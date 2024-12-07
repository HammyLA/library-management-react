package com.lib.library_management_react.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Book findById(Integer bookid) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE bookid = ?", new BookRowMapper(), bookid);
    }

    public List<Book> findByFilter(String title, List<String> genres) {
        if (genres.isEmpty()) {
            return jdbcTemplate.query("SELECT * FROM Book WHERE title LIKE ?", new BookRowMapper(), "%" + title + "%");
        } else {
            String sql = "SELECT * FROM Book WHERE title LIKE ? AND genre IN ("
                    + String.join(",", Collections.nCopies(genres.size(), "?")) + ")";

            List<Object> params = new ArrayList<>();
            params.add("%" + title + "%");
            params.addAll(genres);

            // Execute the query with the generated SQL and parameters
            return jdbcTemplate.query(sql, new BookRowMapper(), params.toArray());
        }

    }

    public List<Book> isRented(Integer bookid) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookid = ? AND bookid IN (SELECT book FROM Rental)", new BookRowMapper(), bookid);
    }

    public int save(Book book) {
        return jdbcTemplate.update(
                "INSERT INTO Book (title, author, genre, description, year_published) VALUES (?, ?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getYearPublished());
    }

    public int update(Book book) {
        return jdbcTemplate.update(
                "UPDATE Book SET title = ?, author = ?, genre = ?, description = ?, year_published = ? WHERE bookid = ?",
                book.getTitle(), book.getDescription(), book.getYearPublished(),
                book.getBookid());
    }

    public int deleteById(Integer bookid) {
        return jdbcTemplate.update("DELETE FROM Book WHERE bookid = ?", bookid);
    }

    private static final class BookRowMapper implements RowMapper<Book> {
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