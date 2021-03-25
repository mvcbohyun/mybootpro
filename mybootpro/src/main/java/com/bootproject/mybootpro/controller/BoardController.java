package com.bootproject.mybootpro.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootproject.mybootpro.model.Board;
import com.bootproject.mybootpro.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardRepository boardRepository;
	
	@GetMapping("list")
	public String list(Model model) {
	List<Board> boards= boardRepository.findAll();
	model.addAttribute("boards",boards);
	return "board/list";
	}
}
