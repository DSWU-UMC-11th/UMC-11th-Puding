package com.umc.study.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM book ORDER BY book_id");
    }

    public List<Map<String, Object>> findByCategoryId(long categoryId) {
        return jdbcTemplate.queryForList(
                "SELECT * FROM book WHERE category_id = ? ORDER BY book_id",
                categoryId
        );
    }

    public void save(Map<String, Object> body) {
        jdbcTemplate.update(
                "INSERT INTO book (category_id, title, description, is_available) VALUES (?, ?, ?, true)",
                body.get("categoryId"),
                body.get("title"),
                body.get("description")
        );
    }
}
