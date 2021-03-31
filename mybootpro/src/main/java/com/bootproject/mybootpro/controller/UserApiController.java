package com.bootproject.mybootpro.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.bootproject.mybootpro.model.Board;
import com.bootproject.mybootpro.model.User;
import com.bootproject.mybootpro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
class UserApiController {

  private final UserRepository repository;

 


 
  @GetMapping("/users")
  List<User> all() {
		  return repository.findAll(); 
	  
    
  }
 
  @PostMapping("/users")
  User newuser(@RequestBody User newuser) {
    return repository.save(newuser);
  }

  // Single item
  
  @GetMapping("/users/{id}")
  User one(@PathVariable Long id) {
    
    return repository.findById(id).orElse(null);
  }

  @PutMapping("/users/{id}")
  User replaceuser(@RequestBody User newuser, @PathVariable Long id) {
    
	  
    return repository.findById(id)
      .map(user -> {
//        user.setTitle(newuser.getTitle());
//        user.setContent(newuser.getContent());
//    	  user.setBoards(newuser.getBoards());
    	  user.getBoards().clear();
    	  user.getBoards().addAll(newuser.getBoards());
    	  for(Board board : user.getBoards()) {
    		  board.setJoindate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    		  board.setEnabled("Y");
    		  board.setUser(user);
    	  }
        return repository.save(user);
      })
      .orElseGet(() -> {
        newuser.setId(id);
        return repository.save(newuser);
      });
  }

  @DeleteMapping("/users/{id}")
  void deleteuser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}