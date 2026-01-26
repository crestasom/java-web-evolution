package org.example.servlet.dao;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);

    List<User> findAll();
}
