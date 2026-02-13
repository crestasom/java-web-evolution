package org.example.repository;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), username VARCHAR(255), password VARCHAR(255), role VARCHAR(255), active BOOLEAN DEFAULT TRUE)");

        // Seed some data
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        if (count == 0) {
            jdbcTemplate.update(
                    "INSERT INTO users (name, email, username, password, role, active) VALUES ('Admin', 'admin@example.com', 'admin', 'admin123', 'ROLE_ADMIN', TRUE)");
            jdbcTemplate.update(
                    "INSERT INTO users (name, email, username, password, role, active) VALUES ('User', 'user@example.com', 'user', 'user123', 'ROLE_USER', TRUE)");
        }
    }

    public void save(String name, String email, String username, String password, String role) {
        jdbcTemplate.update(
                "INSERT INTO users (name, email, username, password, role, active) VALUES (?, ?, ?, ?, ?, TRUE)", name,
                email, username, password, role);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getBoolean("active")));
    }
}
