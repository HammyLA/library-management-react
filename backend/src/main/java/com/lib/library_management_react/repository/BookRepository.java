package com.lib.library_management_react.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lib.library_management_react.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Book> findByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE title LIKE ?", new BookRowMapper(), "%" + title + "%");
    }

    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO Book (title, description, year_published) VALUES (?, ?, ?)", book.getTitle(), book.getDescription(), book.getYearPublished());
    }

    public int update(Book book) {
        return jdbcTemplate.update("UPDATE Book SET title = ?, description = ?, year_published = ? WHERE bookid = ?", book.getTitle(), book.getDescription(), book.getYearPublished(),
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
            book.setTitle(rs.getString("title"));
            book.setYearPublished(rs.getInt("year_published"));
            book.setDescription(rs.getString("description"));
            return book;
        }
    }
}