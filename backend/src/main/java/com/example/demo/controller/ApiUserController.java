package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/list")
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = userRepository.findAll();
	
		map.put("success", true);
		map.put("users", users);
		return map;
	}
	
	@PostMapping("/signup")
	public Map<String, Object> signUp(@RequestBody User request) {
		User newUser = new User(request.getId(), request.getPw(), request.getName());
		userRepository.save(newUser);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
}
