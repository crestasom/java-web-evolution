package org.example.repository;

import java.util.List;

import org.example.model.User;

public interface UserDao {
	void save(User user);

	List<User> findAll();

}
