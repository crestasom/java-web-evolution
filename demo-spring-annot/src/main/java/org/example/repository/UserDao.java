package org.example.repository;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);

    List<User> findAll();
}
