package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.service.jwt.JwtService;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
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
	
	@PostMapping("/login")
   public Map<String, Object> login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
      Map<String, Object> map = new HashMap<>();
      User loginUser = userRepository.findById(user.getId());
      
      if(loginUser == null || !loginUser.matchPw(user.getPw())) {
         System.out.println("로그인 실패ㅠㅠ");
         map.put("success",false);
         return map;
      }
      
      String token = jwtService.create("userInfo", loginUser, "user");
      Cookie cookie = new Cookie("token", token);
      
      if(user.getSave() == "true") {
         int amount = 60 * 60 * 24 * 7;
         cookie.setMaxAge(amount);
      }
      
      cookie.setPath("/");
      cookie.setHttpOnly(true);
      
      response.addCookie(cookie);
      response.setHeader("Authorization", token);

      
      System.out.println("로그인 성공vv");
      map.put("success", true);
      return map;
   }
}
