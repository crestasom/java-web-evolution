package org.example.repository;

import java.util.List;

import org.example.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name, Pageable pageable);

//	@Query("from User where email=:email")
	@Query(nativeQuery = true, value = "select *from user where user_email=:email")
	List<User> findByEmail(String email);

}
