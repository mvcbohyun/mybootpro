package com.bootproject.mybootpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootproject.mybootpro.model.User;
import com.bootproject.mybootpro.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

	private final UserService userService;
	@GetMapping("/login")
	public String login() {
		return "account/login";
	}
	
	
	@GetMapping("/register") 
	public String register() {
		
		return "account/register"; 
	}
	
	@PostMapping("/register") 
	public String register(User user) {
		System.out.println("111111111111111");
		userService.save(user);
		return "redirect:/"; 
	}

}
