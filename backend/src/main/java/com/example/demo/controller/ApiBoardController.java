package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

	@Autowired
	BoardRepository boardRepository;
	
	@PostMapping("/write")
	public Map<String, Object> write(@RequestBody Board board) {
		Map<String, Object> map = new HashMap<String, Object>();
		boardRepository.save(board);
		
		map.put("success", true);
		return map;
	}
}
