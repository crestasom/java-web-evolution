package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DbInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbInitializer(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), username VARCHAR(255), password VARCHAR(255), role VARCHAR(255), active BOOLEAN DEFAULT TRUE)");

        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM users", Integer.class);
        if (count != null && count == 0) {
            jdbcTemplate.execute(
                    "INSERT INTO users (name, email, username, password, role, active) VALUES ('Admin', 'admin@example.com', 'admin', 'admin123', 'ROLE_ADMIN', TRUE)");
            jdbcTemplate.execute(
                    "INSERT INTO users (name, email, username, password, role, active) VALUES ('User', 'user@example.com', 'user', 'user123', 'ROLE_USER', TRUE)");
        }
    }
}
