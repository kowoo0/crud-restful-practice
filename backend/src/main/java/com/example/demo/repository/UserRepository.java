package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// jpa 문법 - By는 무엇으로 찾겠다는 것이고 Id가 붙었으니 id로 찾겠다는 것이다.
	User findById(String id);
	User findByName(String name);
}
