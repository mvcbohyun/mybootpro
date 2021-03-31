package com.bootproject.mybootpro.service;



import org.springframework.stereotype.Service;

import com.bootproject.mybootpro.model.Board;
import com.bootproject.mybootpro.model.User;
import com.bootproject.mybootpro.repository.BoardRepository;
import com.bootproject.mybootpro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public Board save(String username, Board board) {
		User user =userRepository.findByUsername(username);
		board.setUser(user);
		return boardRepository.save(board);
	}
	
	
}
