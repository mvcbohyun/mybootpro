package com.bootproject.mybootpro.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootproject.mybootpro.model.Board;
import com.bootproject.mybootpro.repository.BoardRepository;
import com.bootproject.mybootpro.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardRepository boardRepository;
	private final BoardValidator boardbalidator;
	
	@GetMapping("/list")
	public String list(Model model) {
	List<Board> boards= boardRepository.findAll();
	model.addAttribute("boards",boards);
	return "board/list";
	}
	
	@GetMapping("/form")
	public String form(@RequestParam(required = false)Long id,Model model) {
		if(id == null) {
			model.addAttribute("board",new Board());
			
		}else {
			Board board =boardRepository.findById(id).orElse(null);
			model.addAttribute("board",board);
		}

	return "board/form";
	}
	
	@PostMapping("/form")
	  public String greetingSubmit(@Valid Board board, BindingResult bindingResult) {
		boardbalidator.validate(board, bindingResult);
		System.out.println(bindingResult.hasErrors());
		if(bindingResult.hasErrors()) {
			return "board/form";
		}
		
		board.setJoindate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		board.setEnabled("Y");
	    boardRepository.save(board);
	    return "redirect:/board/list";
	  }
}
